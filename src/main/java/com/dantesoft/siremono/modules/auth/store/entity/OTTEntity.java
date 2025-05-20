package com.dantesoft.siremono.modules.auth.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.authentication.ott.OneTimeToken;

import java.time.Instant;

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
