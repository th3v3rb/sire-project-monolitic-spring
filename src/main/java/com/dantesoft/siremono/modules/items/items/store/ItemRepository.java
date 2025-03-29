package com.dantesoft.siremono.modules.items.items.store;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
  Page<ItemEntity> findByNameContainingIgnoreCase(
      String name,
      Pageable pageable);

  @Modifying
  @Transactional
  @Query("""
             UPDATE ItemEntity i
             SET i.enabled = :enabled
             WHERE i.id IN :ids
         """)
  int updateStatusByIds(
      @Param("ids") List<UUID> ids,
      @Param("enabled") boolean enabled);

  @Modifying
  @Transactional
  @Query("DELETE FROM ItemEntity i WHERE i.id IN :ids")
  int deleteAllByIds(@Param("ids") List<UUID> ids);

}
