package com.dantesoft.siremono.modules.people.document_types.store;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {
  private DocumentTypeService service;

  /**
   * 
   * @param pageable
   * @return
   */
  public Page<DocumentTypeEntity> all(Pageable pageable) {
    return service.all(pageable);
  }

  /**
   * 
   * 
   * @param documentType
   * @return
   */
  public DocumentTypeEntity save(DocumentTypeEntity documentType) {
    return service.save(documentType);
  }

  /**
   * 
   * 
   * @param id
   * @return
   */
  public Optional<DocumentTypeEntity> findById(UUID id) {
    return service.findById(id);
  }

  /**
   * 
   * 
   * @param documentType
   * @return
   */
  public DocumentTypeEntity delete(DocumentTypeEntity documentType) {
    this.service.delete(documentType);
    return documentType;
  }

}
