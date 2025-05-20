package com.dantesoft.siremono.modules.actor.events;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.actor.actor.actions.AddActorAction;
import com.dantesoft.siremono.modules.actor.actor.actions.AddActorInput;
import com.dantesoft.siremono.modules.customer.customer.events.CustomerCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActorCreatorListener {
  private final CommandExecutor handler;


  @EventListener
  @Async("eventTaskExecutor")
  public void onCustomerCreated(CustomerCreatedEvent event) {
    log.info("Customer created, creating respective actor on the system. time: {}", event.getTimestamp());

    var customer = event.getSource();

    var arg = AddActorInput
            .builder()
            .name(customer.getName())
            .description(customer.getDescription())
            .socialReason(customer.getSocialReason())
            .personKind(customer.getPersonKind())
            .build();

    handler.execute(AddActorAction.class, arg);

  }

  //TODO supplier case
}
