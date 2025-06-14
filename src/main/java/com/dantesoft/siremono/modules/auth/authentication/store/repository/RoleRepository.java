package com.dantesoft.siremono.modules.auth.authentication.store.repository;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
  Optional<RoleEntity> findByNameContainingIgnoreCase(String name);
}
