package com.dantesoft.siremono.modules.items.variant.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class VariantEntity extends AbstractEntity {
  @ManyToOne
  private ItemEntity item;
  private BigDecimal price;
  private Integer quantity;

}
