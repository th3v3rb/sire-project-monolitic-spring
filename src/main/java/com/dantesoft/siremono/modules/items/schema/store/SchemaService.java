package com.dantesoft.siremono.modules.items.schema.store;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SchemaService {
  private final SchemaRepository variantRepository;

  public long count() {
    return variantRepository.count();
  }

  public SchemaEntity save(SchemaEntity draft) {
    return variantRepository.save(draft);
  }
}
