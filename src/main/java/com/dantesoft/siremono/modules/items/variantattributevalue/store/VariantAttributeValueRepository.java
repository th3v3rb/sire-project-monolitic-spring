package com.dantesoft.siremono.modules.items.variantattributevalue.store;

import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantAttributeValueRepository extends JpaRepository<VariantAttributeValueEntity, UUID> {

  Page<VariantAttributeValueEntity> findAllByDefinition(VariantAttributeDefinitionEntity definition, Pageable pageable);
}
