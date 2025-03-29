package com.dantesoft.siremono.modules.items.items.store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.items.items.ItemErrors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository repository;

  public Page<ItemEntity> all(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<ItemEntity> allBySearchParam(
      String searchParam,
      Pageable pageable) {
    return repository.findByNameContainingIgnoreCase(searchParam, pageable);
  }

  public ItemEntity save(ItemEntity item) {
    return repository.save(item);
  }

  public Optional<ItemEntity> findById(UUID id) {
    return repository.findById(id);
  }

  public ItemEntity findByIdOrFail(UUID id) {
    return repository.findById(id)
        .orElseThrow(() -> new ItemErrors.NotFoundException(id));
  }

  public ItemEntity delete(ItemEntity item) {
    repository.delete(item);
    return item;
  }

  public int updateStatusWhereIdsIn(List<UUID> ids, boolean enabled) {
    return repository.updateStatusByIds(ids, enabled);
  }

  public int deleteAllByIds(List<UUID> ids) {
    return repository.deleteAllByIds(ids);
  }
}
