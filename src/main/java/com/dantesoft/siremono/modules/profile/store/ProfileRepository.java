package com.dantesoft.siremono.modules.profile.store;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {

	@Query("""
			SELECT profile FROM ProfileEntity profile
			            WHERE profile.user = :user
			""")
	Optional<ProfileEntity> findByUser(@Param("user") AccountEntity user);
}
