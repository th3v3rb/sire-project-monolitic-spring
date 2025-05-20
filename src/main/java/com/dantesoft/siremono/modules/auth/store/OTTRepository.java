package com.dantesoft.siremono.modules.auth.store;

import com.dantesoft.siremono.modules.auth.store.entity.OTTEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTTRepository extends JpaRepository<OTTEntity, Long> {
  Optional<OTTEntity> findByTokenValueAndUsedFalse(String tokenValue);

}
