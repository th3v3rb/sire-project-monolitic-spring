package com.dantesoft.siremono.modules.items.variantattributevalue.store;

import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VariantAttributeValueService {
  private final VariantAttributeValueRepository variantAttributeValueRepository;

  public long count() {
    return variantAttributeValueRepository.count();
  }

  public Page<VariantAttributeValueEntity> findAllByDefinition(VariantAttributeDefinitionEntity definition, Pageable pageable) {
    return variantAttributeValueRepository.findAllByDefinition(definition, pageable);
  }

  public VariantAttributeValueEntity save(final VariantAttributeValueEntity variantAttributeValueEntity) {
    return variantAttributeValueRepository.save(variantAttributeValueEntity);
  }

  public Optional<VariantAttributeValueEntity> findById(UUID id) {
    return variantAttributeValueRepository.findById(id);
  }

  public VariantAttributeValueEntity findByIdOrFail(UUID id) {
    return findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find VariantAttributeValueEntity with id: " + id));
  }
}
