package com.dantesoft.siremono.modules.items.brands.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class BrandEntity extends AbstractEntity {

  public BrandEntity(String name) {
    this.name = name;
  }

  private String name;
}
