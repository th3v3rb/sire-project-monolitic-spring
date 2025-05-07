package com.dantesoft.siremono.modules.auth.store.entity;

import java.time.Instant;
import org.springframework.security.authentication.ott.OneTimeToken;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "one_time_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OTTEntity implements OneTimeToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String tokenValue;
  private String username;

  @Column(name = "expires_at", columnDefinition = "TIMESTAMPTZ")
  private Instant expiresAt;

  @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ", updatable = false)
  private Instant createdAt;

  private boolean used;

}
