package com.dantesoft.siremono.modules.auth.profile.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferencesDTO {
  private String menuMode;
  private Boolean darkTheme;
  private String surface;
  private String preset;
  private String primary;
}
