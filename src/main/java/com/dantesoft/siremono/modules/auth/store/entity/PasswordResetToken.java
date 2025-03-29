package com.dantesoft.siremono.modules.auth.store.entity;

import java.util.Date;

import com.dantesoft.siremono.internal.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends AbstractEntity {
  private String token;
  private Date expiryDate;
  @ManyToOne
  private User user;
}
