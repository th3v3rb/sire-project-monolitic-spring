package com.dantesoft.siremono.modules.auth.store;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dantesoft.siremono.modules.auth.store.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
  public Optional<RoleEntity> findByNameContainingIgnoreCase(String name);
}
