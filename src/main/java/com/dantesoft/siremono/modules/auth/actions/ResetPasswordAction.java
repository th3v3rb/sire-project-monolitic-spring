package com.dantesoft.siremono.modules.auth.actions;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.auth.AuthErrors.ValidationException;
import com.dantesoft.siremono.modules.auth.store.services.AccountTokenService;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResetPasswordAction extends AbstractAction<ResetPasswordInput, ResetPasswordOutput> {

  private final AccountTokenService accountTokenService;
  private final AuthService authService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ResetPasswordOutput doExecute() {
    if (!getInput().isPasswordMatch()) {
      throw new ValidationException("Los passwords no coinciden");
    }

    if (accountTokenService.isTokenExpired(getInput().getToken())) {
      accountTokenService.invalidateToken(getInput().getToken());
      throw new ValidationException("El token ha expirado");
    }

    var user = accountTokenService.getUserByToken(getInput().getToken());
    user.setPassword(passwordEncoder.encode(getInput().getPassword()));
    authService.save(user);

    log.info(
        "User with email {} has successfully reset their password",
        user.getEmail());

    var output = new ResetPasswordOutput();
    output.setMessage("Password reset success");
    return output;
  }

}
