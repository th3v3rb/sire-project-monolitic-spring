package com.dantesoft.siremono.internal.actions;

public interface ActionHandlerContract {
  <I extends ActionInputContract, O extends ActionOutputContract> O execute(Class<? extends ActionContract<I, O>> action, I input);
}