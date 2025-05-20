package com.dantesoft.siremono.modules.people.people.store;

import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;
import com.dantesoft.siremono.modules.people.document_types.store.DocumentTypeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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
