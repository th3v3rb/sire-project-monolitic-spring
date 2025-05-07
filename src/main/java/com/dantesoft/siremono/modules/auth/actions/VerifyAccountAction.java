package com.dantesoft.siremono.modules.auth.actions;

import java.time.LocalDateTime;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.auth.AuthErrors.ValidationException;
import com.dantesoft.siremono.modules.auth.store.OTTService;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class VerifyAccountAction extends AbstractCommand<VerifyAccountInput, VerifyAccountOutput> {
  private final OTTService ottService;
  private final AccountService authService;

  @Override
  public VerifyAccountOutput doExecute() {

    var tokenValue = getInput().getToken();
    var tokenReq = OneTimeTokenAuthenticationToken.unauthenticated(tokenValue);
    var ott = ottService.consume(tokenReq);
    var user = authService.findByEmailOrFail(ott.getUsername());

    if (user.getEmailVerifiedAt() != null) {
      throw new ValidationException("Email already verified");
    }

    user.setEmailVerifiedAt(LocalDateTime.now());
    authService.save(user);

    VerifyAccountOutput output = new VerifyAccountOutput();
    output.setMessage("Account verified now, can log in on the app");
    return output;
  }

}
