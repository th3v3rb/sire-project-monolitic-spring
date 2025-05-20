package com.dantesoft.siremono.modules.customer.customercontact.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerContactRepository extends JpaRepository<CustomerContactEntity, UUID> {
}
