package com.dantesoft.siremono.internal.commands;

import java.util.concurrent.CompletableFuture;

public interface CommandExecutor {
  <I extends CommandInput, O extends CommandOutput> O execute(
      Class<? extends Command<I, O>> action, I input);

  <I extends CommandInput, O extends CommandOutput> CompletableFuture<O> executeAsync(
      Class<? extends Command<I, O>> action, I input);
}