package com.dantesoft.siremono.modules.people.document_types.store;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "document_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentTypeEntity extends AbstractEntity{
  private UUID id;
  private String name;
}
