package com.dantesoft.siremono.modules.auth.store.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
  private UUID id;
  private String name;
  private List<PermissionDTO> permissions;
}
