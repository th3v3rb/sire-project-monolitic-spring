package com.dantesoft.siremono.modules.customer.customer.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.actor.actor.store.PersonKind;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends AbstractEntity {
  private String name;
  private String socialReason;
  private String description;
  private PersonKind personKind;
}
