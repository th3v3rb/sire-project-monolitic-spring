package com.dantesoft.siremono.modules.auth.authentication.store.dto;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.OrganizationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
  private UUID id;
  private String username;
  private String email;
  private LocalDateTime emailVerifiedAt;
  private List<RoleDTO> roles;
  private OrganizationEntity organization;
}
