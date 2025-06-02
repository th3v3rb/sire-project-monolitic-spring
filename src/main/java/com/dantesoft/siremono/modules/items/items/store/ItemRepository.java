package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

  @Query("""
      SELECT _item
      FROM ItemEntity _item
      WHERE LOWER(_item.name) LIKE LOWER(CONCAT('%', :name, '%'))
      """
  )
  Page<ItemEntity> findByName(@Param("name") String name, Pageable pageable);


  boolean existsByCategoriesContaining(CategoryEntity category);
}
