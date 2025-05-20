package com.dantesoft.siremono.modules.auth.store.entity;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organizations")
public class OrganizationEntity extends AbstractEntity {

  private String socialReason;
  private String ruc;
  private LocalDateTime deletedAt;

  @OneToMany(fetch = FetchType.EAGER)
  private List<AccountEntity> users;

}
