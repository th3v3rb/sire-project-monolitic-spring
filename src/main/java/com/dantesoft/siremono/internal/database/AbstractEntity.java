package com.dantesoft.siremono.internal.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @CreationTimestamp
  @Column
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  private LocalDateTime updatedAt;

  private boolean enabled = true;

  private LocalDateTime deletedAt;

}