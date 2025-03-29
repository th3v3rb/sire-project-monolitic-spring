package com.dantesoft.siremono.modules.auth.store.services;

import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.auth.store.entity.Permission;
import com.dantesoft.siremono.modules.auth.store.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {
  private final PermissionRepository permissionRepo;

  public Permission save(Permission permission) {
    return this.permissionRepo.save(permission);
  }
}
