package com.dantesoft.siremono.modules.profile.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferencesDTO {
  private Boolean darkMode;
  private String surface;
  private String preset;
  private String primary;
}
