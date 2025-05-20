package com.dantesoft.siremono.modules.profile.store;

import com.dantesoft.siremono.internal.database.AbstractJsonEntity;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private AccountEntity user;

  private String profileImageName;
  private String fullName;
  private String phoneNumber;
  private LocalDate birthDay;
  private String address;
  private String bio;
  
  private boolean recentlyCreated;

}
