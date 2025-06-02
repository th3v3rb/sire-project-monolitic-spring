package com.dantesoft.siremono.modules.auth.authentication.events;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.profile.store.ProfileData;
import com.dantesoft.siremono.modules.auth.profile.store.ProfileEntity;
import com.dantesoft.siremono.modules.auth.profile.store.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountRegisteredListener {
  private final ProfileService profileService;

  @EventListener
  public void onEvent(UserRegisteredEvent event) {
    log.info("User registered event triggered at {}", event.getTimestamp());

    makeProfile(event.getSource());
  }


  private void makeProfile(AccountEntity account) {
    var profile = new ProfileEntity();

    profile.setAccount(account);
    profile.setRecentlyCreated(true);

    var data = new ProfileData();
    data.setMenuMode("static");
    data.setPreset("Lara");
    data.setPrimary("orange");

    profile.setData(data);

    profileService.save(profile);
  }

}