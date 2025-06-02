package com.dantesoft.siremono.modules.auth.authentication.store.entity;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RefreshTokenEntity extends AbstractEntity {
  private String token;
  @ManyToOne
  private AccountEntity account;
  private LocalDateTime expiresAt;
}
