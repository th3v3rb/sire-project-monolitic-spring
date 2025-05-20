package com.dantesoft.siremono.modules.auth.store;

import com.dantesoft.siremono.modules.auth.store.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository roleRepo;

	public RoleEntity getRoleByName(String role) {
		var search = this.roleRepo.findByNameContainingIgnoreCase(role);

		if (search.isEmpty()) {
			throw new RuntimeException("Role not found");
		}
		return search.get();
	}

	public RoleEntity save(RoleEntity role) {
		return this.roleRepo.save(role);
	}
}
