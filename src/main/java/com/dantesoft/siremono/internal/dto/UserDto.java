package com.dantesoft.siremono.internal.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.dantesoft.siremono.modules.auth.store.entity.Organization;
import com.dantesoft.siremono.modules.auth.store.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
  private UUID id;
  private String username;
  private String email;
  private LocalDateTime emailVerifiedAt;
  private Set<Role> roles;
  private Organization organization;
}
