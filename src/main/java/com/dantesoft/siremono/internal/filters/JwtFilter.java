package com.dantesoft.siremono.internal.filters;

import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.RoleEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final AppProperties app;

  @Override
  protected boolean shouldNotFilter(@NotNull HttpServletRequest request) {
    return Arrays
        .stream(app.getWhiteListedEndpoints())
        .anyMatch(e -> e.equals(request.getRequestURI()));
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    String token = resolveToken(request);

    if (!jwtService.isTokenValid(token)) {
      log.warn("Invalid JWT for request to {}", requestURI);
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid jwt");
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

  }

  private Collection<GrantedAuthority> extractAuthorities(Set<RoleEntity> roles) {

    Set<GrantedAuthority> authorities = roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toSet());

    roles.forEach(role -> {
      Set<GrantedAuthority> permissionAuthorities = role.getPermissions().stream()
          .map(permission -> new SimpleGrantedAuthority(permission.getName()))
          .collect(Collectors.toSet());
      authorities.addAll(permissionAuthorities);
    });

    return authorities;
  }

  private String resolveToken(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (header == null || header.isBlank()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing JWT header");
    }

    if (!header.startsWith("Bearer ")) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header must start with 'Bearer '");
    }

    String token = header.substring(7).trim();

    if (token.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT token is empty");
    }

    return token;
  }

}
