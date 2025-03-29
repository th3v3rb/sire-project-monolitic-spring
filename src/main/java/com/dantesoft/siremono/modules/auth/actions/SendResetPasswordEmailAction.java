package com.dantesoft.siremono.modules.auth.actions;

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

import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendResetPasswordEmailAction extends AbstractAction<SendResetPasswordEmailInput, SendResetPasswordEmailOutput> {

  @Value("${internal.app.front.password-reset-url}")
  private String passwordResetUrl;

  private final AccountTokenService accountTokenService;
  private final EmailPublisher emailPublisher;
  private final TemplateEngine templateEngine;
  private final AuthService authService;

  @Override
  public SendResetPasswordEmailOutput doExecute() {
    var user = authService.findByEmail(getInput().getEmail()).orElseThrow(
        () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "User not found"));
    var resetToken = accountTokenService
        .generateEmailVerificationToken(user.getId());

    var emailResetPasswordLink = String.format(passwordResetUrl, resetToken);
    var emailContent = generateEmailContent(
        user.getUsername(), emailResetPasswordLink);

    var email = new Email();
    email.setBody(emailContent);
    email.setSubject("Reset Your Password");
    email.setTo(user.getEmail());
    email.setCc(Collections.emptyList());
    email.setBcc(Collections.emptyList());
    email.setAttachments(Collections.emptyList());

    this.emailPublisher.send(email);

    var output = new SendResetPasswordEmailOutput();
    output.setMessage("Por favor, queda atento a tu bandeja de correo");
    return output;
  }

  private String generateEmailContent(
      String username,
      String emailResetPasswordLink) {
    Context context = new Context();
    context.setVariable("username", username);
    context.setVariable("passwordResetLink", emailResetPasswordLink);

    return templateEngine.process("reset-password-template", context);
  }
}
