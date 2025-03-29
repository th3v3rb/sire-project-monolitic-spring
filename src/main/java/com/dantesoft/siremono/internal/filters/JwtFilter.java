package com.dantesoft.siremono.internal.filters;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.dantesoft.siremono.modules.auth.store.services.CustomUserDetailsService;
import com.dantesoft.siremono.modules.auth.store.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final CustomUserDetailsService userService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    
    final String requestURI = request.getRequestURI();
    
    try {
      String token = resolveToken(request);
      
      if (token == null) {
        log.debug("No token found for request to {}", requestURI);
        filterChain.doFilter(request, response);
        return;
      }
      
      if (!jwtService.isTokenValid(token)) {
        log.debug("Invalid token for request to {}", requestURI);
        filterChain.doFilter(request, response);
        return;
      }
      
      // Use the correct method from your JwtService - extractUsername instead of extractEmail
      String username = jwtService.extractUsername(token);
      if (username == null) {
        log.debug("Could not extract username from token for request to {}", requestURI);
        filterChain.doFilter(request, response);
        return;
      }
      
      // Only set authentication if not already set
      if (SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, Optional.ofNullable(userDetails)
                .map(UserDetails::getAuthorities).orElse(List.of()));
                
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("Authentication set for user {} for request to {}", username, requestURI);
      }
    } catch (Exception e) {
      log.error("Authentication error for request to {}: {}", requestURI, e.getMessage());
    }
    
    filterChain.doFilter(request, response);
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}