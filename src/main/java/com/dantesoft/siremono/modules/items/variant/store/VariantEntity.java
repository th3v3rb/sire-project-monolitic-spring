package com.dantesoft.siremono.modules.items.variant.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VariantEntity extends AbstractEntity {

  private String name;
  private String sku;
  private BigDecimal price;
  private Integer quantity;

  @ManyToOne(optional = false)
  private ItemEntity item;

  @OneToMany(mappedBy = "variant")
  private List<VariantAttributeValueEntity> attributeValues = new ArrayList<>();
}
