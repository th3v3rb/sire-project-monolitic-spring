package com.dantesoft.siremono.modules.customer.customer.events;

import com.dantesoft.siremono.internal.events.AbstractEvent;
import com.dantesoft.siremono.modules.customer.customer.store.CustomerEntity;

public class CustomerCreatedEvent extends AbstractEvent<CustomerEntity> {
  public CustomerCreatedEvent(CustomerEntity source) {
    super(source);
  }
}
