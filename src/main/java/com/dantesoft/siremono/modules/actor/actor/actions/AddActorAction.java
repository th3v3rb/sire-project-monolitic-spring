package com.dantesoft.siremono.modules.actor.actor.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.actor.actor.store.ActorEntity;
import com.dantesoft.siremono.modules.actor.actor.store.ActorService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddActorAction extends AbstractCommand<AddActorInput, AddActorOutput> {
  private final ActorService actorService;

  @Override
  protected AddActorOutput doExecute() {
    var draft = ActorEntity
            .builder()
            .name(getInput().getName())
            .description(getInput().getDescription())
            .socialReason(getInput().getSocialReason())
            .personKind(getInput().getPersonKind())
            .build();

    actorService.save(draft);

    return new AddActorOutput();
  }
}
