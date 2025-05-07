package com.dantesoft.siremono.modules.items.categories.store;

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
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
  Page<CategoryEntity> findByNameContainingIgnoreCase(String name, Pageable Pageble);

  @Modifying
  @Transactional
  @Query("UPDATE CategoryEntity b SET b.enabled = :active WHERE b.id IN :ids")
  int updateActiveStatusByIds(@Param("ids") List<UUID> ids, @Param("active") boolean active);

  @Modifying
  @Transactional
  @Query("DELETE FROM CategoryEntity b WHERE b.id IN :ids")
  void deleteAllByIds(@Param("ids") List<UUID> ids);

}
