package com.dantesoft.siremono.modules.items.categories.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "categories")
public class CategoryEntity extends AbstractEntity {

  public CategoryEntity(String name) {
    this.name = name;
  }

  private String name;
}
