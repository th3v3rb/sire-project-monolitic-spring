package com.dantesoft.siremono.modules.auth.store.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.dantesoft.siremono.internal.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
@Table(name = "organizations")
public class Organization extends AbstractEntity {

  private String socialReason;
  private String ruc;
  private LocalDateTime deletedAt;

  @OneToMany(fetch = FetchType.EAGER)
  private List<User> users;

}
