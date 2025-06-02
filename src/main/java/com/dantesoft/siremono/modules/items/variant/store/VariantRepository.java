package com.dantesoft.siremono.modules.items.variant.store;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, UUID> {

  Page<VariantEntity> findByItemAndNameContainingIgnoreCase(ItemEntity item, String name, Pageable pageable);
}
