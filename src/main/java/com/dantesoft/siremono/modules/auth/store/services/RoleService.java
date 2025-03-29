package com.dantesoft.siremono.modules.auth.store.services;

import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.auth.store.entity.Role;
import com.dantesoft.siremono.modules.auth.store.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepo;

  /**
   * Handle search the role on the database, ignore case
   *
   * @param role
   * @return
   */
  public Role getRoleByName(String role) {
    var search = this.roleRepo.findByNameContainingIgnoreCase(role);

    if (search.isEmpty()) {
      throw new RuntimeException("Role not found");
    }
    return search.get();
  }

  public Role save(Role role) {
    return this.roleRepo.save(role);
  }
}
