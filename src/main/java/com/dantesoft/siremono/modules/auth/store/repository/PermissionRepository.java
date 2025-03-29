package com.dantesoft.siremono.modules.auth.store.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dantesoft.siremono.modules.auth.store.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {

}
