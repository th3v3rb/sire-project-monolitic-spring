package com.dantesoft.siremono.modules.auth.store.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.auth.AuthErrors.NotFoundException;
import com.dantesoft.siremono.modules.auth.store.entity.EmailVerificationToken;
import com.dantesoft.siremono.modules.auth.store.entity.User;
import com.dantesoft.siremono.modules.auth.store.repository.EmailVerificationTokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountTokenService {
  @Value("${internal.app.token.expiration}")
  private long EMAIL_TOKEN_EXPIRATION;
  private final EmailVerificationTokenRepository tokenRepository;

  /**
   * Genera un token único para la verificación del correo electrónico
   *
   * @param userId UUID del usuario al que se le enviará el token
   * @return Token único
   */
  @Transactional
  public String generateEmailVerificationToken(UUID userId) {
    UUID token = UUID.randomUUID();
    var user = new User();
    user.setId(userId);

    LocalDateTime expirationDate = LocalDateTime.now()
        .plus(EMAIL_TOKEN_EXPIRATION, ChronoUnit.MINUTES);
    EmailVerificationToken verificationToken = EmailVerificationToken.builder()
        .token(token).user(user).expirationDate(expirationDate).build();
    tokenRepository.save(verificationToken);

    return token.toString();
  }

  /**
   * Elimina un token después de que haya sido validado o expirado
   *
   * @param token Token a eliminar
   */
  @Transactional
  public void invalidateToken(String token) {
    Optional<EmailVerificationToken> tokenOpt = tokenRepository
        .findByToken(UUID.fromString(token));
    tokenOpt.ifPresent(tokenRepository::delete);
  }

  /**
   * Verifica si un token ha expirado
   *
   * @param token Token a verificar
   * @return true si el token está expirado, false en caso contrario
   */
  @Transactional
  public boolean isTokenExpired(String token) {
    Optional<EmailVerificationToken> tokenOpt = tokenRepository
        .findByToken(UUID.fromString(token));
    if (tokenOpt.isEmpty()) {
      return true;
    }

    EmailVerificationToken emailVerificationToken = tokenOpt.get();
    return LocalDateTime.now()
        .isAfter(emailVerificationToken.getExpirationDate());
  }

  public User getUserByToken(String token) {
    var parsedToken = UUID.fromString(token);
    return this.tokenRepository.findByToken(parsedToken) //
        .orElseThrow(() -> new NotFoundException(parsedToken)) //
        .getUser();
  }
}
