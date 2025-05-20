package com.dantesoft.siremono.modules.actor.actorcontact.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class ActorContactEntity extends AbstractEntity {
  private String contact;
  private ContactType contactType;
  private String comment;
  private boolean main;
}
