package com.dantesoft.siremono.modules.items.categories.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class CategoryEntity extends AbstractEntity {
  private String name;
  private boolean enabled;
}
