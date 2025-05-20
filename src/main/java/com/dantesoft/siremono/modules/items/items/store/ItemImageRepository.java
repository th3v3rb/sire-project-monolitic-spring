package com.dantesoft.siremono.modules.items.items.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImageEntity, UUID> {

	@Query("""
		SELECT image FROM ItemImageEntity image
		WHERE image.item = :item
		AND image.main = true
		AND image.enabled = true
	""")
	Optional<ItemImageEntity> findMainByItem(@Param("item") ItemEntity item);
	
}
