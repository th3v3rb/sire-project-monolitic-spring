package com.dantesoft.siremono.internal.actions;

/**
 * Represents an action for the application
 *
 * @param <I> Application input argument
 * @param <O> Application output result
 */
public interface ActionContract<I extends ActionInputContract, O extends ActionOutputContract> {
  O execute(I input);
}
