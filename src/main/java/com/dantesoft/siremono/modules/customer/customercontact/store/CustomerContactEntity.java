package com.dantesoft.siremono.modules.customer.customercontact.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.actor.actorcontact.store.ContactType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerContactEntity extends AbstractEntity {
  private String contact;
  private ContactType contactType;
  private String comment;
  private boolean main;

}
