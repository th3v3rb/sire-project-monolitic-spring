package com.dantesoft.siremono.modules.items.brands.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "brands")
public class BrandEntity extends AbstractEntity {
  private String name;
  private boolean enabled;
}
