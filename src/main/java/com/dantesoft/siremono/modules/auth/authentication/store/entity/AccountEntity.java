package com.dantesoft.siremono.modules.auth.authentication.store.entity;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity extends AbstractEntity implements UserDetails {
  @Column(nullable = false)
  private String username;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  private LocalDateTime accountExpiredAt;
  private LocalDate accountLockedAt;
  private LocalDateTime credentialsExpiredAt;
  @Column(nullable = false)
  private boolean enabled;

  private LocalDateTime emailVerifiedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  private OrganizationEntity organization;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    Optional.ofNullable(roles).orElse(Set.of()).forEach(role -> role.getPermissions()
        .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName()))));
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountExpiredAt == null || accountExpiredAt.isAfter(LocalDateTime.now());
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountLockedAt == null;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsExpiredAt == null || credentialsExpiredAt.isAfter(LocalDateTime.now());
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

}
