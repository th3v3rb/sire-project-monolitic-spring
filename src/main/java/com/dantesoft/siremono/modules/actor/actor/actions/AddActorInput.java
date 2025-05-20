package com.dantesoft.siremono.modules.actor.actor.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.modules.actor.actor.store.PersonKind;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddActorInput implements CommandInput {
  private String name;
  private String socialReason;
  private String description;
  private PersonKind personKind;
}
