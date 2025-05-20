package com.dantesoft.siremono.modules.customer.customer.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

  @Query("""
            SELECT c FROM CustomerEntity c 
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(c.socialReason) LIKE LOWER(CONCAT('%', :query, '%'))
          """)
  Page<CustomerEntity> searchByNameOrSocialReason(Pageable pageable, String query);

}
