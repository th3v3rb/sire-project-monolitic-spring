package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
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
public class ItemService {
  private final ItemRepository repository;

  public List<ItemEntity> findAll() {
    return repository.findAll();
  }

  public boolean existByCategoryContaining(CategoryEntity category) {
    return repository.existsByCategoriesContaining(category);
  }

  public Page<ItemEntity> allBySearchParam(String searchParam, Pageable pageable) {
    return repository.findByName(searchParam, pageable);
  }

  public ItemEntity save(ItemEntity item) {
    return repository.save(item);
  }

  public Optional<ItemEntity> findById(UUID id) {
    return repository.findById(id);
  }

  public ItemEntity findByIdOrFail(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  public void delete(ItemEntity item) {
    repository.delete(item);
  }

  public void deleteAll(List<ItemEntity> list) {
    repository.deleteAll(list);
  }

  public List<ItemEntity> findAllById(List<UUID> ids) {
    return repository.findAllById(ids);
  }
}
