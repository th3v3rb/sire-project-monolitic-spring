package com.dantesoft.siremono.modules.auth.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.dantesoft.siremono.connectors.email.Email;
import com.dantesoft.siremono.connectors.email.EmailPublisher;
import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.auth.store.services.AccountTokenService;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendVerificationEmailAction extends AbstractAction<SendVerificationEmailInput, SendVerificationEmailOutput> {

  private final AccountTokenService accountTokenService;
  private final EmailPublisher emailPublisher;
  private final TemplateEngine templateEngine;
  private final AuthService authService;

  @Value("${internal.app.front.email-verification-url}")
  private String emailVerificationUrl;

  @Override
  public SendVerificationEmailOutput doExecute() {
    var user = authService.findByEmail(getInput().getEmail()).orElseThrow(
        () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "User not found"));
    String verificationToken = accountTokenService
        .generateEmailVerificationToken(user.getId());

    String emailVerificationLink = String
        .format(emailVerificationUrl, verificationToken);
    String emailContent = generateEmailContent(
        user.getUsername(), emailVerificationLink);

    var email = new Email();
    email.setBody(emailContent);
    email.setSubject("Verify your account");
    email.setTo(user.getEmail());
    email.setCc(List.of());
    email.setBcc(List.of());
    email.setAttachments(List.of());

    this.emailPublisher.send(email);

    SendVerificationEmailOutput output = new SendVerificationEmailOutput();
    output.setMessage("Por favor, queda atento a tu bandeja de correo");
    return output;
  }

  private String generateEmailContent(
      String username,
      String emailVerificationLink) {
    Context context = new Context();
    context.setVariable("username", username);
    context.setVariable("emailVerificationLink", emailVerificationLink);

    return templateEngine.process("email-verification-template", context);
  }
}
