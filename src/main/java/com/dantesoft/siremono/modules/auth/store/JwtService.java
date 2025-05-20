package com.dantesoft.siremono.modules.auth.store;

import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.store.entity.PermissionEntity;
import com.dantesoft.siremono.modules.auth.store.entity.RoleEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

  @Value("${internal.jwt.secret}")
  private String secret;

  @Value("${internal.jwt.expiration}")
  private long expirationTime;

  public Claims getAllClaims(String token) {
    return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
  }

  public boolean isValid(String token, UserDetails user) {
    String username = extractUsername(token);
    return username.equals(user.getUsername()) && isTokenNotExpired(token);
  }

  public String generateToken(AccountEntity user) {
    List<Map<String, Object>> rolesData = user.getRoles().stream().map(this::mapRoleToMap).collect(Collectors.toList());

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiration = now.plusSeconds(expirationTime / 1000);

    return Jwts.builder().signWith(getSignInKey())
        .issuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
        .expiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
        .subject(user.getUsername()).claim("id", user.getId()).claim("email", user.getEmail())
        .claim("mail_verified_at", getEmailVerifiedAt(user)).claim("roles", rolesData).compact();
  }

  public <T> T extractClaim(String token, Function<Claims, T> resolver) {
    Claims claims = getAllClaims(token);
    return resolver.apply(claims);
  }

  public UUID extractUserId(String token) {
    String rawId = extractClaim(token, claims -> claims.get("id", String.class));
    return UUID.fromString(rawId);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public LocalDateTime extractMailVerifiedAt(String token) {
    String mailVerifiedAtString = extractClaim(token, claims -> claims.get("mail_verified_at", String.class));
    return mailVerifiedAtString != null
        ? LocalDateTime.parse(mailVerifiedAtString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        : null;
  }

  public String extractEmail(String token) {
    return extractClaim(token, claims -> claims.get("email", String.class));
  }

  public Set<RoleEntity> extractRoles(String token) {
    if (token == null || token.trim().isEmpty()) {
      return Collections.emptySet();
    }

    return extractClaim(token, claims -> {
      Object rolesObj = claims.get("roles");
      if (!(rolesObj instanceof List<?>)) {
        return Collections.emptySet();
      }

      @SuppressWarnings("unchecked")
      List<Map<?, ?>> rolesList = (List<Map<?, ?>>) rolesObj;
      return rolesList.stream().map(this::createRoleFromMap).filter(Objects::nonNull)
          .collect(Collectors.toSet());
    });
  }

  public boolean isTokenValid(String authToken) {
    try {
      SecretKey signingKey = getSignInKey();
      JwtParser jwtParser = Jwts.parser().verifyWith(signingKey).build();
      jwtParser.parseSignedClaims(authToken).getPayload();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private Map<String, Object> mapRoleToMap(RoleEntity role) {
    Map<String, Object> roleMap = new HashMap<>();
    roleMap.put("id", role.getId().toString());
    roleMap.put("name", role.getName());
    roleMap.put("permissions",
        role.getPermissions().stream().map(this::mapPermissionToMap).collect(Collectors.toList()));
    return roleMap;
  }

  private Map<String, Object> mapPermissionToMap(PermissionEntity permission) {
    Map<String, Object> permissionMap = new HashMap<>();
    permissionMap.put("id", permission.getId().toString());
    permissionMap.put("name", permission.getName());
    return permissionMap;
  }

  private String getEmailVerifiedAt(AccountEntity user) {
    return Objects.toString(user.getEmailVerifiedAt(), null);
  }

  private RoleEntity createRoleFromMap(Map<?, ?> roleMap) {
    try {
      String name = (String) roleMap.get("name");
      String id = (String) roleMap.get("id");
      Object permissionsObj = roleMap.get("permissions");

      if (name == null || id == null) {
        return null;
      }

      Set<PermissionEntity> permissions = extractPermissions(permissionsObj);

      RoleEntity role = new RoleEntity();
      role.setId(UUID.fromString(id));
      role.setName(name);
      role.setPermissions(permissions);

      return role;

    } catch (Exception e) {
      log.error("Error creating role from map: {}", e.getMessage());
      return null;
    }
  }

  private Set<PermissionEntity> extractPermissions(Object permissionsObj) {
    if (!(permissionsObj instanceof List<?>)) {
      return Collections.emptySet();
    }

    return ((List<?>) permissionsObj).stream().filter(item -> item instanceof Map)
        .map(item -> createPermissionFromMap((Map<?, ?>) item)).filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  private PermissionEntity createPermissionFromMap(Map<?, ?> permMap) {
    try {
      String name = (String) permMap.get("name");
      String id = (String) permMap.get("id");

      if (name == null || id == null) {
        return null;
      }

      var permission = new PermissionEntity();
      permission.setId(UUID.fromString(id));
      permission.setName(name);

      return permission;

    } catch (Exception e) {
      log.error("Error creating permission from map: {}", e.getMessage());
      return null;
    }
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private boolean isTokenNotExpired(String token) {
    return !getAllClaims(token).getExpiration().before(new Date());
  }

}
