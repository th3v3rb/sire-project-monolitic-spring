package com.dantesoft.siremono.modules.auth.store.entity;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
public class PermissionEntity extends AbstractEntity{
  @Column(unique = true, nullable = false)
  private String name;
}
