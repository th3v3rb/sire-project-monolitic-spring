package com.dantesoft.siremono.modules.items.categories.store;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
  Page<CategoryEntity> findByNameContainingIgnoreCase(String name, Pageable Pageble);

  @Modifying
  @Transactional
  @Query("DELETE FROM CategoryEntity b WHERE b.id IN :ids")
  void deleteAllByIds(@Param("ids") List<UUID> ids);


  @Query("""
          select _item.categories FROM ItemEntity _item
          WHERE _item = :item
      """)
  Set<CategoryEntity> findCategoryEntitiesByItem(@Param("item") ItemEntity item);
}
