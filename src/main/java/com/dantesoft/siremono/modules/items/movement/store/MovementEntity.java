package com.dantesoft.siremono.modules.items.movement.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MovementEntity extends AbstractEntity {

  @ManyToOne
  private ItemEntity item;
  private String itemName;
  private Integer quantity;
  private MovementType type;
  private String comment;
}
