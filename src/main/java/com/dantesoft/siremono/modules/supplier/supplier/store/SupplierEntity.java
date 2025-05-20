package com.dantesoft.siremono.modules.supplier.supplier.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.actor.actor.store.PersonKind;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierEntity extends AbstractEntity {
  private String name;
  private String socialReason;
  private String description;
  private PersonKind personKind;
}
