package com.dantesoft.siremono.internal.commands;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Primary
public class DefaultCommandExecutor implements CommandExecutor {
    private final CommandFactory commandFactory;
    
    @Override
    public <I extends CommandInput, O extends CommandOutput> O execute(
            Class<? extends Command<I, O>> actionClass, I input) {
        try {
            Command<I, O> actionInstance = commandFactory.getCommand(actionClass);
            return actionInstance.execute(input);
        } catch (Exception e) {
            log.error("Error executing action: {}", actionClass.getName(), e);
            throw new RuntimeException("Error executing action", e);
        }
    }
    
    @Async
    @Override
    public <I extends CommandInput, O extends CommandOutput> CompletableFuture<O> executeAsync(
            Class<? extends Command<I, O>> actionClass, I input) {
        try {
            Command<I, O> actionInstance = commandFactory.getCommand(actionClass);
            var out = actionInstance.execute(input);
            return CompletableFuture.completedFuture(out);
        } catch (Exception e) {
            log.error("Error executing action: {}", actionClass.getName(), e);
            throw new RuntimeException("Error executing async action", e);
        }
    }
}