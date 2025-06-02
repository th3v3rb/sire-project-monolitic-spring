package com.dantesoft.siremono.modules.items.categories.store;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository repository;

  public List<CategoryEntity> findAll() {
    return repository.findAll();
  }

  public CategoryEntity findByIdOrFail(UUID id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  public List<CategoryEntity> findAllById(List<UUID> ids) {
    return repository.findAllById(ids);
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

  public void deleteWhereAllInIds(List<UUID> list) {
    repository.deleteAllByIds(list);
  }

  public Set<CategoryEntity> findCategoriesByItemId(UUID itemId) {
    var item = new ItemEntity();
    item.setId(itemId);
    return repository.findCategoryEntitiesByItem(item);
  }
}
