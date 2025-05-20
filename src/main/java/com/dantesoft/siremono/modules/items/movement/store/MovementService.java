package com.dantesoft.siremono.modules.items.movement.store;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovementService {
  private final MovementRepository repository;

  public MovementEntity save(final MovementEntity entity) {
    return repository.save(entity);
  }

  public MovementEntity findByIdOrFail(final UUID id) {
    return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }


  public Page<MovementEntity> listAll(
          final Pageable pageable,
          final ItemEntity item
  ) {
      return repository.findAllByItem(pageable, item);
  }

}
