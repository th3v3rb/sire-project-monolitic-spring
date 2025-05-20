package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.modules.items.items.store.views.ItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

  @Query("""
        SELECT i FROM ItemEntity i
        WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))
      """)
  Page<ItemView> findByName(@Param("name") String name, Pageable pageable);


  @Modifying
  @Transactional
  @Query("""
          UPDATE ItemEntity i
          SET i.enabled = :enabled
          WHERE i.id IN :ids
      """)
  int updateStatusByIds(@Param("ids") List<UUID> ids, @Param("enabled") boolean enabled);

  @Modifying
  @Transactional
  @Query("DELETE FROM ItemEntity i WHERE i.id IN :ids")
  void deleteAllByIds(@Param("ids") List<UUID> ids);
}
