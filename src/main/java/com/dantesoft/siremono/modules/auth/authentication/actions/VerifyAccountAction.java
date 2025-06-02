package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.exception.ValidationException;
import com.dantesoft.siremono.modules.auth.authentication.store.services.AccountService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.OTTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;

import java.time.LocalDateTime;
import java.util.Optional;

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

    var email = Optional.ofNullable(ott)
        .map(OneTimeToken::getUsername)
        .orElseThrow(() -> new ValidationException("Username not found on token"));

    var user = authService.findByEmailOrFail(email);

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
