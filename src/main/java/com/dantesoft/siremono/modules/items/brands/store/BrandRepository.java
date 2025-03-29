package com.dantesoft.siremono.modules.items.brands.store;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, UUID>, JpaSpecificationExecutor<BrandEntity> {

  @Modifying
  @Transactional
  @Query("UPDATE BrandEntity b SET b.enabled = :enabled WHERE b.id IN :ids")
  int updateStatusByIds(@Param("ids") List<UUID> ids, @Param("enabled") boolean status);

  @Modifying
  @Transactional
  @Query("DELETE FROM BrandEntity b WHERE b.id IN :ids")
  int deleteAllByIds(@Param("ids") List<UUID> ids);

}
