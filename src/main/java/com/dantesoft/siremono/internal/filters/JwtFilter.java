package com.dantesoft.siremono.internal.filters;

import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.auth.store.JwtService;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.store.entity.RoleEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final AppProperties app;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return Arrays.stream(app.getWhiteListedEndpoints())
        .anyMatch(e -> e.equals(request.getRequestURI()));
  }


  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    try {
      String token = resolveToken(request);

      if (token == null) {
        log.warn("No JWT cookie for request to {}", requestURI);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return;
      }
      
      if (!jwtService.isTokenValid(token)) {
        log.warn("Invalid JWT for request to {}", requestURI);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return;
      }

      String email = jwtService.extractEmail(token);

      if (email == null || email.isBlank()) {
        log.warn("Empty email in JWT for request to {}", requestURI);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return;
      }

      if (SecurityContextHolder.getContext().getAuthentication() == null) {
        Set<RoleEntity> roles = jwtService.extractRoles(token);
        Collection<GrantedAuthority> authorities = extractAuthorities(roles);

        var user = new AccountEntity();
        user.setId(jwtService.extractUserId(token));
        user.setUsername(jwtService.extractUsername(token));
        user.setEmail(jwtService.extractEmail(token));
        user.setEmailVerifiedAt(jwtService.extractMailVerifiedAt(token));
        user.setRoles(roles);

        var auth = new UsernamePasswordAuthenticationToken(user, null, authorities);
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("Authenticated '{}' for {}", email, requestURI);
      }

      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      log.error("Error in JwtFilter for {}: {}", requestURI, ex.getMessage(), ex);
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
  }

  private Collection<GrantedAuthority> extractAuthorities(Set<RoleEntity> roles) {
    Set<GrantedAuthority> authorities = new HashSet<>();

    Set<GrantedAuthority> roleAuthorities =
        roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
            .collect(Collectors.toSet());
    authorities.addAll(roleAuthorities);

    roles.forEach(role -> {
      Set<GrantedAuthority> permissionAuthorities = role.getPermissions().stream()
          .map(permission -> new SimpleGrantedAuthority(permission.getName()))
          .collect(Collectors.toSet());
      authorities.addAll(permissionAuthorities);
    });

    return authorities;
  }

  private String resolveToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      return null;
    }

    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("jwt")) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
