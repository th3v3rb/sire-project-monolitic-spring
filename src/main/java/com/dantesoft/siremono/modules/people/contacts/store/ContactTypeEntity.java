package com.dantesoft.siremono.modules.people.contacts.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "contact_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTypeEntity extends AbstractEntity {
  private String name;
}
