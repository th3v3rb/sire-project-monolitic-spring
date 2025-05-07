package com.dantesoft.siremono.modules.items.brands.store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.items.brands.BrandErrors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
	private final BrandRepository brandRepo;

	public BrandEntity findByIdOrFail(UUID id) {
		return brandRepo.findById(id).orElseThrow(() -> new BrandErrors.NotFoundException(id));
	}

	public Page<BrandEntity> searchBrands(String search, Pageable pageable) {
		return brandRepo.findByNameContainingIgnoreCase(search, pageable);
	}

	public void updateStatusWhereAllInIds(List<UUID> list, boolean status) {
		brandRepo.updateStatusByIds(list, status);
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
