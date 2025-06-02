package com.dantesoft.siremono.modules.auth.authentication.events;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.auth.authentication.actions.SendVerificationEmailAction;
import com.dantesoft.siremono.modules.auth.authentication.actions.SendVerificationEmailInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VerificationEmailSendListener {
  private final CommandExecutor handler;

  @EventListener
  @Async("eventTaskExecutor")
  public void onEvent(UserRegisteredEvent event) {
    log.info("Send email verification triggered at {}", event.getTimestamp());

    var input = new SendVerificationEmailInput();
    input.setEmail(event.getSource().getEmail());

    handler.execute(SendVerificationEmailAction.class, input);

  }
}
