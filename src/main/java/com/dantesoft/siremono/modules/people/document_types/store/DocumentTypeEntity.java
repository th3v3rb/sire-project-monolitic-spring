package com.dantesoft.siremono.modules.people.document_types.store;

import java.util.UUID;

import com.dantesoft.siremono.internal.database.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
