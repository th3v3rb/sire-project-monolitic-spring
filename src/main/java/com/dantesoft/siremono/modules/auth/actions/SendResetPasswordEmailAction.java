package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.connectors.email.DefaultEmailPublisher;
import com.dantesoft.siremono.connectors.email.dto.Email;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.store.OTTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@RequiredArgsConstructor
public class SendResetPasswordEmailAction
        extends AbstractCommand<SendResetPasswordEmailInput, SendResetPasswordEmailOutput> {

  private final AppProperties appProperties;
  private final OTTService ottService;
  private final DefaultEmailPublisher mailAdapter;
  private final TemplateEngine templateEngine;
  private final AccountService userService;

  @Override
  public SendResetPasswordEmailOutput doExecute() {
    var user = userService.findByEmailOrFail(getInput().getEmail());
    var resetToken = ottService.generate(new GenerateOneTimeTokenRequest(user.getUsername()));

    var emailResetPasswordLink = String.format(appProperties.getFront().passwordResetUrl(), resetToken);
    var emailContent = generateEmailContent(user.getUsername(), emailResetPasswordLink);

    var email = new Email();
    email.setSubject("Reset Your Password");
    email.setTo(user.getEmail());
    email.setBody(emailContent);

    mailAdapter.send(email);

    var output = new SendResetPasswordEmailOutput();
    output.setMessage("Please see your mail inbox");
    return new SendResetPasswordEmailOutput();
  }

  private String generateEmailContent(String username, String emailResetPasswordLink) {
    var context = new Context();
    context.setVariable("username", username);
    context.setVariable("passwordResetLink", emailResetPasswordLink);

    return templateEngine.process("reset-password-template", context);
  }
}
