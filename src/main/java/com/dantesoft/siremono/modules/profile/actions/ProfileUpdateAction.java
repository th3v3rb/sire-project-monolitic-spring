package com.dantesoft.siremono.modules.profile.actions;

import static com.dantesoft.siremono.internal.Utils.parseBase64;
import java.time.LocalDate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.profile.store.ProfileData;
import com.dantesoft.siremono.modules.profile.store.ProfileEntity;
import com.dantesoft.siremono.modules.profile.store.ProfileService;
import com.dantesoft.siremono.modules.profile.store.dto.PreferencesDTO;
import com.dantesoft.siremono.modules.profile.store.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileUpdateAction extends AbstractCommand<ProfileUpdateInput, ProfileUpdateOutput> {
  private final ProfileService profileService;
  private final UploadAdapter uploadAdapter;
  private final AccountService authService;
  private final AppProperties app;

  @Override
  protected ProfileUpdateOutput doExecute() {
    AccountEntity user = authService.getAuthenticatedUser();
    var profile = profileService.findByUser(user)
        .orElseThrow(() -> new UsernameNotFoundException("Profile not found"));

    mergeProfile(profile, getInput().getProfile());
    mergePreferences(profile.getData(), getInput().getPreferences());

    profileService.save(profile);

    return null;
  }

  private void mergeProfile(ProfileEntity profile, ProfileDTO req) {
    if (req == null)
      return;

    profile.setRecentlyCreated(false);

    if (req.getProfileImage() != null) {
      var name = upload(profile, req.getProfileImage());
      profile.setProfileImageName(name);
    }

    if (req.getAddress() != null) {
      profile.setAddress(req.getAddress());
    }

    if (req.getBio() != null) {
      profile.setBio(req.getBio());
    }

    if (req.getBirthDate() != null) {
      String isoDate = req.getBirthDate().substring(0, 10); 
      LocalDate birthDate = LocalDate.parse(isoDate);
      profile.setBirthDay(birthDate);
  }


    if (req.getFullName() != null) {
      profile.setFullName(req.getFullName());
    }

    if (req.getPhoneNumber() != null) {
      profile.setPhoneNumber(req.getPhoneNumber());
    }

  }


  private void mergePreferences(ProfileData data, PreferencesDTO req) {
    if (req == null)
      return;

    if (req.getDarkTheme() != data.isDarkTheme()) {
      data.setDarkTheme(req.getDarkTheme());
    }
    if (req.getPreset() != null) {
      data.setPreset(req.getPreset());
    }
    if (req.getPrimary() != null) {
      data.setPrimary(req.getPrimary());
    }
    if (req.getSurface() != null) {
      data.setSurface(req.getSurface());
    }
    if(req.getMenuMode() != null) {
      data.setMenuMode(req.getMenuMode());
    }
  }

  private void mergeNotifications() {

  }

  private String upload(ProfileEntity profile, String base64) {
    String name = null;
    try {
      var bucket = app.getStorage().profileImagesBucket();
      var parsed = parseBase64(base64);

      name = uploadAdapter.uploadFromBytes(bucket,
          "profile-" + profile.getId() + "-" + System.currentTimeMillis(),
          parsed.contentType(),
          parsed.payload());

    } catch (Exception e) {

    }

    return name;
  }



}
