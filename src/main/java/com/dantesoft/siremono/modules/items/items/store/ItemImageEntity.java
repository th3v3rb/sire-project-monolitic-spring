package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item_images")
public class ItemImageEntity extends AbstractEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private ItemEntity item;

  private String name;
  private boolean main;
  private boolean enabled;
}
