package com.dantesoft.siremono.modules.customer.customer.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.events.DomainEventPublisher;
import com.dantesoft.siremono.modules.customer.customer.events.CustomerCreatedEvent;
import com.dantesoft.siremono.modules.customer.customer.store.CustomerEntity;
import com.dantesoft.siremono.modules.customer.customer.store.CustomerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddCustomerAction extends AbstractCommand<AddCustomerInput, AddCustomerOutput> {
  private final CustomerService customerService;
  private final DomainEventPublisher domainEventPublisher;

  @Override
  protected AddCustomerOutput doExecute() {
    var draft = CustomerEntity
        .builder()
        .name(getInput().getName())
        .description(getInput().getDescription())
        .socialReason(getInput().getSocialReason())
        .personKind(getInput().getPersonKind())
        .build();

    var entity = customerService.save(draft);

    domainEventPublisher.publish(new CustomerCreatedEvent(entity));

    return new AddCustomerOutput();
  }
}
