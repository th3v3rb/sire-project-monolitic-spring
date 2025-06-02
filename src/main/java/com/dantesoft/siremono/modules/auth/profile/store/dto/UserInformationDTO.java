package com.dantesoft.siremono.modules.auth.profile.store.dto;

import com.dantesoft.siremono.modules.auth.authentication.store.dto.AccountDTO;
import lombok.*;

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
