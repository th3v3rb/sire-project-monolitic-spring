package com.dantesoft.siremono.modules.profile.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.modules.profile.store.dto.NotificationDTO;
import com.dantesoft.siremono.modules.profile.store.dto.PreferencesDTO;
import com.dantesoft.siremono.modules.profile.store.dto.ProfileDTO;
import com.dantesoft.siremono.modules.profile.store.dto.SecuritySettingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateInput implements CommandInput {
  private ProfileDTO profile;
  private PreferencesDTO preferences;
  private NotificationDTO notifications;
  private SecuritySettingDTO security;
}
