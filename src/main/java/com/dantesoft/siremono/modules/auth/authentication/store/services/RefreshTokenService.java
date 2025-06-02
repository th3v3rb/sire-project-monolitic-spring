package com.dantesoft.siremono.modules.auth.authentication.store.services;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.RefreshTokenEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.repository.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshTokenEntity generateAndStoreToken(AccountEntity account) {
    var refreshToken = new RefreshTokenEntity();
    refreshToken.setAccount(account);
    refreshToken.setExpiresAt(LocalDateTime.now().plusDays(7));
    refreshToken.setToken(UUID.randomUUID().toString());
    return refreshTokenRepository.save(refreshToken);
  }

  public AccountEntity findAccountByRefreshToken(String refreshToken) {
    return refreshTokenRepository
        .findByToken(refreshToken)
        .orElseThrow(() -> new EntityNotFoundException("Not found refresh token"))
        .getAccount();
  }
}
