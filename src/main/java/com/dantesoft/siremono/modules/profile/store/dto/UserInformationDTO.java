package com.dantesoft.siremono.modules.profile.store.dto;

import com.dantesoft.siremono.modules.auth.store.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationDTO {
  private AccountDTO account;
  private ProfileDTO profile;
  private PreferencesDTO preferences;
  private NotificationDTO notifications;
  private SecuritySettingDTO security;
}
