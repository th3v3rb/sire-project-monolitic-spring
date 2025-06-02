package com.dantesoft.siremono.modules.items.variantattributevalue.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.items.variant.store.VariantEntity;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class VariantAttributeValueEntity extends AbstractEntity {
  @ManyToOne
  private VariantEntity variant;

  @ManyToOne
  private VariantAttributeDefinitionEntity definition;

  private String value;
  private BigDecimal plusPrice;
}
