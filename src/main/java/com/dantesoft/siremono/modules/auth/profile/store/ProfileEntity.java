package com.dantesoft.siremono.modules.auth.profile.store;

import com.dantesoft.siremono.internal.database.AbstractJsonEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_profiles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity extends AbstractJsonEntity<ProfileData> {

  @OneToOne
  private AccountEntity account;

  private String profileImageName;
  private String fullName;
  private String phoneNumber;
  private LocalDate birthDay;
  private String address;
  private String bio;

  private boolean recentlyCreated;

}
