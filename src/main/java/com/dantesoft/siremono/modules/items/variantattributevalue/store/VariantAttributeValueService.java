package com.dantesoft.siremono.modules.items.variantattributevalue.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariantAttributeValueService {
  private final VariantAttributeValueRepository variantAttributeValueRepository;

  public long count() {
    return variantAttributeValueRepository.count();
  }

  public VariantAttributeValueEntity save(final VariantAttributeValueEntity variantAttributeValueEntity) {
    return variantAttributeValueRepository.save(variantAttributeValueEntity);
  }
}
