package com.dantesoft.siremono.internal.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEventPublisher {

  private final ApplicationEventPublisher delegate;

  public <T> void publish(Event<T> event) {
    delegate.publishEvent(event);
  }
}