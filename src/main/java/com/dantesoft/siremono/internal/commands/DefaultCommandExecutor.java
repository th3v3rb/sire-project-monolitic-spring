package com.dantesoft.siremono.internal.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
@Primary
public class DefaultCommandExecutor implements CommandExecutor {
  private final ApplicationContext ctx;

  @Override
  public <I extends CommandInput, O extends CommandOutput> O execute(
      Class<? extends Command<I, O>> actionClass, I input) {
    Command<I, O> cmd = ctx.getBean(actionClass);
    return cmd.execute(input);
  }

  @Async("commandExecutor")
  @Override
  public <I extends CommandInput, O extends CommandOutput> CompletableFuture<O> executeAsync(
      Class<? extends Command<I, O>> actionClass, I input) {
    Command<I, O> cmd = ctx.getBean(actionClass);
    return CompletableFuture.completedFuture(cmd.execute(input));
  }

}