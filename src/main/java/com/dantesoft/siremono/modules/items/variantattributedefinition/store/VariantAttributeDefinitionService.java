package com.dantesoft.siremono.modules.items.variantattributedefinition.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VariantAttributeDefinitionService {
  private final VariantAttributeDefinitionRepository variantAttributeDefinitionRepository;

  public long count() {
    return variantAttributeDefinitionRepository.count();
  }

  public Page<VariantAttributeDefinitionEntity> findAllByName(String filter, Pageable pageable) {
    return variantAttributeDefinitionRepository.findAllByNameContainingIgnoreCase(filter, pageable);
  }

  public VariantAttributeDefinitionEntity save(VariantAttributeDefinitionEntity variantAttributeDefinitionEntity) {
    return variantAttributeDefinitionRepository.save(variantAttributeDefinitionEntity);
  }

  public void deleteAllById(List<UUID> ids) {
    variantAttributeDefinitionRepository.deleteAllById(ids);
  }

  public void deleteById(UUID id) {
    variantAttributeDefinitionRepository.deleteById(id);
  }

  public VariantAttributeDefinitionEntity findByIdOrFail(UUID id) {
    return variantAttributeDefinitionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

}
