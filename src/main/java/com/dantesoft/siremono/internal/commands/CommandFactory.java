package com.dantesoft.siremono.internal.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandFactory {
    private final ApplicationContext applicationContext;
    private final Map<Class<?>, CommandCreator<?>> registeredCommands = new ConcurrentHashMap<>();
    
    /**
     * Interface for command creation strategies
     */
    private interface CommandCreator<T> {
        T create();
    }
    
    /**
     * Register a command class with its creator function
     */
    public <T extends Command<?, ?>> void registerCommand(Class<T> commandClass, CommandCreator<T> creator) {
        registeredCommands.put(commandClass, creator);
        log.debug("Registered command: {}", commandClass.getName());
    }
    
    /**
     * Get or create a command instance
     */
    @SuppressWarnings("unchecked")
    public <T extends Command<?, ?>> T getCommand(Class<T> commandClass) {
        CommandCreator<T> creator = (CommandCreator<T>) registeredCommands.get(commandClass);
        
        if (creator != null) {
            return creator.create();
        }
        
        // If not registered, try to instantiate using reflection and autowire dependencies
        try {
            Constructor<?>[] constructors = commandClass.getDeclaredConstructors();
            if (constructors.length > 0) {
                Constructor<?> constructor = constructors[0];
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] params = new Object[paramTypes.length];
                
                // Resolve dependencies from Spring context
                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = applicationContext.getBean(paramTypes[i]);
                }
                
                T command = (T) constructor.newInstance(params);
                
                // Register for future use
                registerCommand(commandClass, () -> command);
                
                return command;
            }
            throw new RuntimeException("No suitable constructor found for: " + commandClass.getName());
        } catch (Exception e) {
            log.error("Failed to create command: {}", commandClass.getName(), e);
            throw new RuntimeException("Error creating command instance", e);
        }
    }
}