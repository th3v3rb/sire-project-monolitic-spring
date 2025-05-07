package com.dantesoft.siremono.internal.commands;

/**
 * Abstract base class for all actions that provides access to the input
 * 
 * @param <I> Application input argument
 * @param <O> Application output result
 */
public abstract class AbstractCommand<I extends CommandInput, O extends CommandOutput> implements Command<I, O> {

  private I input;

  /**
   * Returns the current input for this action
   */
  protected I getInput() {
    return this.input;
  }

  /**
   * Template method that sets the input and then executes the action
   */
  @Override
  public final O execute(I input) {
    this.input = input;
    return doExecute();
  }

  /**
   * The actual execution method that subclasses should implement
   */
  protected abstract O doExecute();
}
