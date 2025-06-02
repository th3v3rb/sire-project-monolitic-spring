package com.dantesoft.siremono.modules.auth.profile.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileData {
  private String menuMode;
  private boolean darkTheme;
  private String surface;
  private String preset;
  private String primary;
}
