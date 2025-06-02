package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.exception.ValidationException;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.services.AccountService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.JwtService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Slf4j
@RequiredArgsConstructor
public class LoginUserAction extends AbstractCommand<LoginUserInput, LoginUserOutput> {

  private final AuthenticationManager authManager;
  private final AccountService accountService;

  private final JwtService jwtService;
  private final RefreshTokenService refreshTokenService;

  @Override
  public LoginUserOutput doExecute() {
    var email = getInput().getEmail();
    var password = getInput().getPassword();
    var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
    authManager.authenticate(authenticationToken);

    var account = accountService.findByEmailOrFail(email);

    doCheck(account);
    var refreshToken = refreshTokenService.generateAndStoreToken(account);

    String jwt = jwtService.generateToken(account);

    LoginUserOutput output = new LoginUserOutput();
    output.setJwt(jwt);
    output.setRefreshToken(refreshToken);

    return output;
  }

  private void doCheck(AccountEntity account) {
    if (!account.isCredentialsNonExpired()) {
      throw new ValidationException("The credentials has been expired");
    }

    if (!account.isAccountNonExpired()) {
      throw new ValidationException("The account has been expired");
    }

    if (!account.isEnabled()) {
      throw new ValidationException("The account has been banned");
    }
  }
}