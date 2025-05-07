package com.dantesoft.siremono.modules.auth.store;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dantesoft.siremono.modules.auth.store.entity.OTTEntity;

@Repository
public interface OTTRepository extends JpaRepository<OTTEntity, Long> {
  Optional<OTTEntity> findByTokenValueAndUsedFalse(String tokenValue);

}
