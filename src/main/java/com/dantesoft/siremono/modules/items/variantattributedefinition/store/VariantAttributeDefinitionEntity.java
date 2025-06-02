package com.dantesoft.siremono.modules.items.variantattributedefinition.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class VariantAttributeDefinitionEntity extends AbstractEntity {
  private String name;
}
