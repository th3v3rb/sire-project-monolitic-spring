package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.modules.items.items.store.views.ItemView;
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

  public Page<ItemEntity> all(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<ItemView> allBySearchParam(String searchParam, Pageable pageable) {
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

  public int updateStatusWhereIdsIn(List<UUID> ids, boolean enabled) {
    return repository.updateStatusByIds(ids, enabled);
  }

  public void deleteAll(List<ItemEntity> list) {
    repository.deleteAll(list);
  }

  public List<ItemEntity> findAllById(List<UUID> ids) {
    return repository.findAllById(ids);
  }
}
