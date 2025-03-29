package com.dantesoft.siremono.modules.auth.actions;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.auth.AuthErrors.ValidationException;
import com.dantesoft.siremono.modules.auth.store.entity.User;
import com.dantesoft.siremono.modules.auth.store.services.AccountTokenService;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class VerifyAccountAction extends AbstractAction<VerifyAccountInput, VerifyAccountOutput> {

  private final AccountTokenService accountTokenService;
  private final AuthService authService;

  @Override
  public VerifyAccountOutput doExecute() {
    var user = verifyTokenAndGetUser(getInput());
    verifyEmailNotVerified(user);
    verifyAndInvalidateToken(getInput().getToken());

    user.setEmailVerifiedAt(LocalDateTime.now());
    authService.save(user);

    log.info(
        "Account {} verified their email now: {}", user.getId(),
        user.getEmail());

    return createVerificationOutput();
  }

  private User verifyTokenAndGetUser(VerifyAccountInput input) {
    var user = accountTokenService.getUserByToken(input.getToken());
    if (user == null)
      throw new ValidationException("Usuario no encontrado");

    return user;
  }

  private void verifyEmailNotVerified(User user) {
    if (user.getEmailVerifiedAt() != null) {
      throw new ValidationException("La cuenta ya ha sido verificada");
    }
  }

  private void verifyAndInvalidateToken(String token) {
    if (accountTokenService.isTokenExpired(token)) {
      accountTokenService.invalidateToken(token);
      throw new ValidationException("El token ha expirado");
    }
  }

  private VerifyAccountOutput createVerificationOutput() {
    VerifyAccountOutput output = new VerifyAccountOutput();
    output.setMessage("Account verified now, can log in on the app");
    return output;
  }
}
