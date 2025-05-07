package com.dantesoft.siremono.modules.profile.store;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import lombok.RequiredArgsConstructor;

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
