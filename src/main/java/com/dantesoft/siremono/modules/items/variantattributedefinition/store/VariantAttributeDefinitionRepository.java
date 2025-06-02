package com.dantesoft.siremono.modules.items.variantattributedefinition.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantAttributeDefinitionRepository extends JpaRepository<VariantAttributeDefinitionEntity, UUID> {

  Page<VariantAttributeDefinitionEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
