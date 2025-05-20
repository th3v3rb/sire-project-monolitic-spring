package com.dantesoft.siremono.modules.actor.actor.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class ActorEntity extends AbstractEntity {
  private String name;
  private String socialReason;
  private String description;
  private PersonKind personKind;

}
