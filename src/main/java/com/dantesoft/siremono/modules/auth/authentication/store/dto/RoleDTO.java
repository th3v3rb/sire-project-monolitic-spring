package com.dantesoft.siremono.modules.auth.authentication.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
  private UUID id;
  private String name;
  private List<PermissionDTO> permissions;
}
