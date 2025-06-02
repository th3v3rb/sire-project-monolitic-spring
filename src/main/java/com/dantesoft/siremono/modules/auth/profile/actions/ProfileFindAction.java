package com.dantesoft.siremono.modules.auth.profile.actions;

import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.auth.authentication.store.dto.AccountDTO;
import com.dantesoft.siremono.modules.auth.authentication.store.dto.RoleMapper;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.services.AccountService;
import com.dantesoft.siremono.modules.auth.profile.store.ProfileData;
import com.dantesoft.siremono.modules.auth.profile.store.ProfileEntity;
import com.dantesoft.siremono.modules.auth.profile.store.ProfileService;
import com.dantesoft.siremono.modules.auth.profile.store.dto.PreferencesDTO;
import com.dantesoft.siremono.modules.auth.profile.store.dto.ProfileDTO;
import com.dantesoft.siremono.modules.auth.profile.store.dto.UserInformationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProfileFindAction extends AbstractCommand<ProfileFindInput, ProfileFindOutput> {
  private final AccountService userService;
  private final ProfileService userProfileService;
  private final UploadAdapter uploads;
  private final AppProperties app;

  @Override
  protected ProfileFindOutput doExecute() {
    var account = userService.getAuthenticatedUser();
    var profile = userProfileService
        .findByAccountOrFail(account);

    var data = profile.getData();

    var accountDTO = makeUser(account);
    var profileDTO = makeProfile(profile);
    var preferencesDTO = makePreferences(data);

    var dto = UserInformationDTO.builder()
        .account(accountDTO)
        .profile(profileDTO)
        .preferences(preferencesDTO)
        .notifications(null)
        .security(null)
        .build();

    return AbstractOutput.of(ProfileFindOutput.class, dto);
  }

  private AccountDTO makeUser(AccountEntity user) {
    var roles = RoleMapper.mapAll(user.getRoles());

    return AccountDTO.builder()
        .email(user.getEmail())
        .emailVerifiedAt(user.getEmailVerifiedAt())
        .id(user.getId())
        .organization(user.getOrganization())
        .roles(roles)
        .username(user.getUsername())
        .build();
  }

  private ProfileDTO makeProfile(ProfileEntity entity) {
    return ProfileDTO.builder()
        .address(entity.getAddress())
        .bio(entity.getBio())
        .birthDate(entity.getBirthDay() == null ? null : entity.getBirthDay().toString())
        .fullName(entity.getFullName())
        .phoneNumber(entity.getPhoneNumber())
        .profileImage(makeSignedUrl(entity.getProfileImageName()))
        .recentlyCreated(entity.isRecentlyCreated())
        .build();
  }

  private String makeSignedUrl(String name) {
    String url = null;
    var bucket = app.getStorage().profileImagesBucket();
    try {
      url = uploads.getPresignedUrl(bucket, name, 604800); // 7 days
    } catch (Exception ex) {
      log.error("Error generating signed url", ex);
    }
    return url;
  }

  private PreferencesDTO makePreferences(ProfileData data) {
    var preferences = new PreferencesDTO();
    preferences.setDarkTheme(data.isDarkTheme());
    preferences.setPreset(data.getPreset());
    preferences.setPrimary(data.getPrimary());
    preferences.setSurface(data.getSurface());
    preferences.setMenuMode(data.getMenuMode());
    return preferences;
  }
}
