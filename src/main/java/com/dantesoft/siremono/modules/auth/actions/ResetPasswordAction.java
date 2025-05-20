package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.exception.ValidationException;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.store.OTTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class ResetPasswordAction extends AbstractCommand<ResetPasswordInput, ResetPasswordOutput> {

  private final OTTService ottService;
  private final AccountService authService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ResetPasswordOutput doExecute() {
    if (!getInput().isPasswordMatch()) {
      throw new ValidationException("The passwords doesnt match");
    }

    var token = OneTimeTokenAuthenticationToken.unauthenticated(getInput().getToken());
    ottService.consume(token);

    var email = token.getName();
    var user = authService.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    var encoded = passwordEncoder.encode(getInput().getPassword());
    user.setPassword(encoded);

    var output = new ResetPasswordOutput();
    output.setMessage("Password reset success");
    return new ResetPasswordOutput();
  }

}
