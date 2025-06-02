package com.dantesoft.siremono.modules.auth.authentication.store.services;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.RoleEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepo;

  public List<RoleEntity> findAll() {
    return roleRepo.findAll();
  }


  public RoleEntity findByName(String roleName) {
    return roleRepo.findByNameContainingIgnoreCase(roleName)
        .orElseThrow(() -> new RuntimeException("Role not found"));
  }

  public RoleEntity save(RoleEntity role) {
    return roleRepo.save(role);
  }
}
