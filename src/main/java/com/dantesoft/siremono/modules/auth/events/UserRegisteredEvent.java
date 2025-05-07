package com.dantesoft.siremono.modules.auth.events;

import com.dantesoft.siremono.internal.events.AbstractEvent;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;

public class UserRegisteredEvent extends AbstractEvent<AccountEntity> {
	public UserRegisteredEvent(AccountEntity source) {
		super(source);
	}
}