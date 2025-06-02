package com.dantesoft.siremono.modules.items.variant.store;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VariantService {
  private final VariantRepository variantRepository;

  public long count() {
    return variantRepository.count();
  }

  public Page<VariantEntity> list(
      final Pageable pageable,
      final String filter,
      final ItemEntity item
  ) {
    return variantRepository.findByItemAndNameContainingIgnoreCase(item, filter, pageable);
  }

  public VariantEntity save(VariantEntity draft) {
    return variantRepository.save(draft);
  }
}
