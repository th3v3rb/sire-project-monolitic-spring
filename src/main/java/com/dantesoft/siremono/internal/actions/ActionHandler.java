package com.dantesoft.siremono.internal.actions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Primary
public class ActionHandler implements ActionHandlerContract {
  private final ApplicationContext applicationContext;

  @Override
  public <I extends ActionInputContract, O extends ActionOutputContract> O execute(
      Class<? extends ActionContract<I, O>> actionClass,
      I input) {
    try {
      ActionContract<I, O> actionInstance = applicationContext
          .getBean(actionClass);
      return actionInstance.execute(input);
    } catch (Exception e) {
      log.error("Error executing action: {}", actionClass.getName(), e);
      throw new RuntimeException("Error executing action", e);
    }
  }
}