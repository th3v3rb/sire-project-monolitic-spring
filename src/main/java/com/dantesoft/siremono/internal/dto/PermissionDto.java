package com.dantesoft.siremono.internal.dto;

import java.util.List;

import com.dantesoft.siremono.modules.auth.store.entity.Role;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
  private String id;
  private String name;

  @ManyToMany
  @JoinTable(
      name = "role_permissions",
      joinColumns = @JoinColumn(
          name = "permission_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "permission_id",
          referencedColumnName = "id"))
  private List<Role> roles;
}
