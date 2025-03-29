package com.dantesoft.siremono.modules.auth.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dantesoft.siremono.modules.auth.store.entity.EmailVerificationToken;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, UUID> {
  Optional<EmailVerificationToken> findByToken(UUID token);

  Optional<EmailVerificationToken> findByUserId(UUID userId);
}
