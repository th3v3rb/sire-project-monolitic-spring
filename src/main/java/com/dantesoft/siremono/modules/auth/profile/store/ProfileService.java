package com.dantesoft.siremono.modules.auth.profile.store;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import jakarta.persistence.EntityNotFoundException;
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

  public Optional<ProfileEntity> findByAccount(AccountEntity user) {
    return repository.findByAccount(user);
  }

  public ProfileEntity findByAccountOrFail(AccountEntity user) {
    return findByAccount(user)
        .orElseThrow(() -> new EntityNotFoundException("Not found profile for user " + user.getEmail()));
  }

}
