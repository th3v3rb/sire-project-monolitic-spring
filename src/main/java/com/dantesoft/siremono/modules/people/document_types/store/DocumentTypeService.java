package com.dantesoft.siremono.modules.people.document_types.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {
  private final DocumentTypeRepository repository;


  public DocumentTypeEntity save(DocumentTypeEntity entity) {
    return repository.save(entity);
  }

  public DocumentTypeEntity findByIdOrFail(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }
}
