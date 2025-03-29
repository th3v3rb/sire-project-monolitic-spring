package com.dantesoft.siremono.modules.items.brands.store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.items.brands.BrandErrors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepo;

  public BrandEntity findByIdOrFail(UUID id) {
    return brandRepo.findById(id)
        .orElseThrow(() -> new BrandErrors.NotFoundException(id));
  }

  public Page<BrandEntity> searchBrands(
      String search,
      Boolean status,
      Pageable pageable) {
    Specification<BrandEntity> spec = Specification.where(null);

    // Add name search criteria if search parameter is provided
    if (search != null && !search.trim().isEmpty()) {
      spec = spec.and((root, _, builder) -> {
        var like = builder.like(
            builder.lower(root.get("name")), "%" + search.toLowerCase() + "%");
        return like;
      });
    }

    // Add status filter if status parameter is provided
    if (status != null) {
      spec = spec.and(
          (root, _, builder) -> builder.equal(root.get("enabled"), status));
    }

    return brandRepo.findAll(spec, pageable);
  }

  public int updateStatusWhereAllInIds(List<UUID> list, boolean status) {
    return brandRepo.updateStatusByIds(list, status);
  }

  public int deleteWhereAllInIds(List<UUID> list) {
    return brandRepo.deleteAllByIds(list);
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
