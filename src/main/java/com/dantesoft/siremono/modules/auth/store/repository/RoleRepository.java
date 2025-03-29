package com.dantesoft.siremono.modules.auth.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dantesoft.siremono.modules.auth.store.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  public Optional<Role> findByNameContainingIgnoreCase(String name);
}
