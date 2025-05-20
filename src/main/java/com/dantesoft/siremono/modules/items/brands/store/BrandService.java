package com.dantesoft.siremono.modules.items.brands.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepo;

  public BrandEntity findByIdOrFail(UUID id) {
    return brandRepo
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  public Page<BrandEntity> searchBrands(String search, Pageable pageable) {
    return brandRepo.findByNameContainingIgnoreCase(search, pageable);
  }

  public void deleteWhereAllInIds(List<UUID> list) {
    brandRepo.deleteAllByIds(list);
  }

  public BrandEntity save(BrandEntity brand) {
    return this.brandRepo.save(brand);
  }

  public Optional<BrandEntity> findById(UUID id) {
    return this.brandRepo.findById(id);
  }

  public void delete(BrandEntity brand) {
    brandRepo.delete(brand);
  }

}
