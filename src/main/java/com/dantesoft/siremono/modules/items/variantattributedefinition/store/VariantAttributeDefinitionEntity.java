package com.dantesoft.siremono.modules.items.variantattributedefinition.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class VariantAttributeDefinitionEntity extends AbstractEntity {
  private String name;
  private String type;
  private List<String> allowedValues;
}
