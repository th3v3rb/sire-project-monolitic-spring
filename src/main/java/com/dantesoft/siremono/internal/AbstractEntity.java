package com.dantesoft.siremono.internal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @CreationTimestamp
  @Column(updatable = true, nullable = true)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = true)
  private LocalDateTime updatedAt;
  
  @JsonIgnore  // Esto evita que se envíe en la respuesta JSON
  private String clave;
  private LocalDateTime deletedAt;

}