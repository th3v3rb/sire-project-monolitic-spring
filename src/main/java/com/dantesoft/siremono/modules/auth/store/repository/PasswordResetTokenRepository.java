package com.dantesoft.siremono.modules.auth.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dantesoft.siremono.modules.auth.store.entity.PasswordResetToken;
import com.dantesoft.siremono.modules.auth.store.entity.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
  Optional<PasswordResetToken> findByToken(String token);

  Optional<PasswordResetToken> findByUser(User user);
}
