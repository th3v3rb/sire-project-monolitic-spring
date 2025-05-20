package com.dantesoft.siremono.modules.profile.store.dto;

import com.dantesoft.siremono.modules.auth.store.dto.AccountDTO;
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
