package com.dantesoft.siremono.modules.auth.profile.store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileDTO {
  private String profileImage;
  private String fullName;
  private String phoneNumber;
  private String address;
  private String birthDate;
  private String bio;
  private Boolean recentlyCreated;
}
