package com.dantesoft.siremono.modules.auth.authentication.events;

import com.dantesoft.siremono.internal.events.AbstractEvent;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;

public class UserRegisteredEvent extends AbstractEvent<AccountEntity> {
  public UserRegisteredEvent(AccountEntity source) {
    super(source);
  }
}