package com.dantesoft.siremono.modules.auth.authentication.store.repository;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

}
