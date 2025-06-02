package com.dantesoft.siremono.modules.auth.authentication.store.services;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.OTTEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.repository.OTTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ott.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OTTService implements OneTimeTokenService {

  private static final Duration VALIDITY = Duration.ofMinutes(15);

  private final OTTRepository tokenRepository;
  private final Clock clock;

  @NonNull
  @Override
  public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
    Instant now = Instant.now(clock);
    Instant expires = now.plus(VALIDITY);

    OTTEntity token = OTTEntity.builder()
        .tokenValue(UUID.randomUUID().toString())
        .username(request.getUsername())
        .createdAt(now)
        .expiresAt(expires)
        .used(false)
        .build();

    tokenRepository.save(token);
    return new DefaultOneTimeToken(token.getTokenValue(), token.getUsername(), now);
  }

  @Override
  public OneTimeToken consume(OneTimeTokenAuthenticationToken authToken) {
    Instant now = Instant.now(clock);

    OTTEntity token = tokenRepository
        .findByTokenValueAndUsedFalse(authToken.getTokenValue())
        .orElseThrow(() -> new BadCredentialsException("Invalid token or used token"));

    if (token.getExpiresAt().isBefore(now)) {
      throw new BadCredentialsException("Token expired");
    }

    token.setUsed(true);
    tokenRepository.save(token);

    return new DefaultOneTimeToken(token.getTokenValue(), token.getUsername(), now);
  }
}

