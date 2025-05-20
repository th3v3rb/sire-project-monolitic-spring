package com.dantesoft.siremono.modules.people.document_types.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, UUID> {

}
