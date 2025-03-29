package com.dantesoft.siremono.internal.actions.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.ActionContract;
import com.dantesoft.siremono.internal.actions.ActionHandler;
import com.dantesoft.siremono.internal.actions.ActionInputContract;
import com.dantesoft.siremono.internal.actions.ActionOutputContract;

@Component
public class AbstractRest {

  @Autowired
  protected ActionHandler handler;

  protected <I extends ActionInputContract, O extends ActionOutputContract> ResponseEntity<O> handle(
      Class<? extends ActionContract<I, O>> actionClass,
      I input) {
    O output = handler.execute(actionClass, input);
    return ResponseEntity.ok(output);
  }
}
