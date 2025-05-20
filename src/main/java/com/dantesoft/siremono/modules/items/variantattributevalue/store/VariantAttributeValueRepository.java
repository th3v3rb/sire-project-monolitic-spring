package com.dantesoft.siremono.modules.items.variantattributevalue.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantAttributeValueRepository extends JpaRepository<VariantAttributeValueEntity, UUID> {

}
