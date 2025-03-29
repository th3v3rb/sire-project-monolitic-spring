package com.dantesoft.siremono.modules.people.people.store;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
  Page<PersonEntity> findBySocialReasonContainingIgnoreCase(String socialReason, Pageable pageable);
}
