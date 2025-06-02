package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.services.JwtService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RefreshAccessTokenAction extends AbstractCommand<RefreshAccessTokenInput, RefreshAccessTokenOutput> {
  private final RefreshTokenService refreshTokenService;
  private final JwtService jwtService;

  @Override
  protected RefreshAccessTokenOutput doExecute() {

    if (getInput().getRefreshToken() == null || getInput().getRefreshToken().isEmpty()) {
      throw new IllegalArgumentException("Refresh token is empty");
    }

    AccountEntity account = refreshTokenService.findAccountByRefreshToken(getInput().getRefreshToken());
    String accessToken = jwtService.generateToken(account);

    var output = new RefreshAccessTokenOutput();
    output.setJwt(accessToken);

    return output;
  }
}
