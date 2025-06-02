package com.dantesoft.siremono.modules.auth.authentication.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
  private String id;
  private String name;
}
