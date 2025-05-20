package com.dantesoft.siremono.modules.supplier.supplier.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
  @Query("""
            SELECT s FROM SupplierEntity s
            WHERE s.deletedAt IS NULL
               AND
               (LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(s.socialReason) LIKE LOWER(CONCAT('%', :query, '%')))
          """)
  Page<SupplierEntity> searchByNameOrSocialReason(Pageable pageable, String query);

}
