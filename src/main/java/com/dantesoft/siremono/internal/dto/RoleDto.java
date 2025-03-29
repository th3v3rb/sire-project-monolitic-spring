package com.dantesoft.siremono.internal.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
  private UUID uuid;
  private String name;
  private List<PermissionDto> permissions;
}
