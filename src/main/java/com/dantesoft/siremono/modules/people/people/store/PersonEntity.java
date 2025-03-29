package com.dantesoft.siremono.modules.people.people.store;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;
import com.dantesoft.siremono.modules.people.document_types.store.DocumentTypeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String socialReason;
  private String document;
  @ManyToOne
  private DocumentTypeEntity documentType;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @OneToMany
  private Set<ContactEntity> contacts;
}
