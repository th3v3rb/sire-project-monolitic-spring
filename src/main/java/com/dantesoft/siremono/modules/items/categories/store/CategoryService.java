package com.dantesoft.siremono.modules.items.categories.store;

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
public class CategoryService {
  private final CategoryRepository repository;

  public CategoryEntity findByIdOrFail(UUID id) {
    return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  public Page<CategoryEntity> all(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<CategoryEntity> findByNameContainingIgnoreCase(
          String searchParam,
          Pageable pageable) {
    return repository.findByNameContainingIgnoreCase(searchParam, pageable);
  }

  public CategoryEntity save(CategoryEntity category) {
    return repository.save(category);
  }

  public Optional<CategoryEntity> findById(UUID id) {
    return repository.findById(id);
  }

  public void delete(CategoryEntity category) {
    repository.delete(category);
  }

  public int updateStatusWhereAllInIds(List<UUID> list, boolean status) {
    return repository.updateActiveStatusByIds(list, status);
  }

  public void deleteWhereAllInIds(List<UUID> list) {
    repository.deleteAllByIds(list);
  }
}
