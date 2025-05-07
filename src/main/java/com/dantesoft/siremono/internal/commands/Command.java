package com.dantesoft.siremono.internal.commands;

/**
 * Represents an action for the application
 *
 * @param <I> Application input argument
 * @param <O> Application output result
 */
public interface Command<I extends CommandInput, O extends CommandOutput> {
  O execute(I input);
}
