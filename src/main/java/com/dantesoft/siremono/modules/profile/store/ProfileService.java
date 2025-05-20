package com.dantesoft.siremono.modules.profile.store;

import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository repository;

	public ProfileEntity save(ProfileEntity entity) {
		return repository.save(entity);
	}

	public Optional<ProfileEntity> findByUser(AccountEntity user) {
		return repository.findByUser(user);
	}

}
