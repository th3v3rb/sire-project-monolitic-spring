package com.dantesoft.siremono.modules.auth.profile.store;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {

  Optional<ProfileEntity> findByAccount(AccountEntity account);
}
