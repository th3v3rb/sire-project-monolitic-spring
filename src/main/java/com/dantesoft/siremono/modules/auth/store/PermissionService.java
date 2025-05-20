package com.dantesoft.siremono.modules.auth.store;

import com.dantesoft.siremono.modules.auth.store.entity.PermissionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {
	private final PermissionRepository repository;

	public PermissionEntity save(PermissionEntity permission) {
		return repository.save(permission);
	}
}
