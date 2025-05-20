package com.dantesoft.siremono.modules.people.contacts.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity extends AbstractEntity {
  private String description;
  @ManyToOne
  private ContactTypeEntity contactType;
  @ManyToOne
  private PersonEntity person;
  private boolean enabled;
}
