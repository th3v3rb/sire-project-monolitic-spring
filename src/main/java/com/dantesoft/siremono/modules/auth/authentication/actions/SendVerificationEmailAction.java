package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.connectors.email.DefaultEmailPublisher;
import com.dantesoft.siremono.connectors.email.dto.Email;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.auth.authentication.store.services.AccountService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.OTTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@RequiredArgsConstructor
public class SendVerificationEmailAction extends AbstractCommand<SendVerificationEmailInput, SendVerificationEmailOutput> {

  private final OTTService ottService;
  private final DefaultEmailPublisher emailPublisher;
  private final TemplateEngine templateEngine;
  private final AccountService userService;
  private final AppProperties appProperties;

  @Override
  public SendVerificationEmailOutput doExecute() {
    var user = userService.findByEmailOrFail(getInput().getEmail());

    var req = new GenerateOneTimeTokenRequest(user.getEmail());
    var ott = ottService.generate(req);
    String emailVerificationLink =
        String.format(appProperties.getFront().emailVerificationUrl(), ott.getTokenValue());
    String emailContent = generateEmailContent(user.getUsername(), emailVerificationLink);

    var email = new Email();
    email.setSubject("Verify your account");
    email.setTo(user.getEmail());
    email.setBody(emailContent);

    this.emailPublisher.send(email);

    SendVerificationEmailOutput output = new SendVerificationEmailOutput();
    output.setMessage("Plase, see your mail inbox");
    return output;
  }

  private String generateEmailContent(String username, String emailVerificationLink) {
    var context = new Context();
    context.setVariable("username", username);
    context.setVariable("emailVerificationLink", emailVerificationLink);

    return templateEngine.process("email-verification-template", context);
  }
}
