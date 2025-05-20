package com.dantesoft.siremono.modules.items.movement.store;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, UUID> {

  @Query("SELECT e FROM MovementEntity e WHERE e.item = :item")
  Page<MovementEntity> findAllByItem(Pageable pageable, @Param("item") ItemEntity item);

}
