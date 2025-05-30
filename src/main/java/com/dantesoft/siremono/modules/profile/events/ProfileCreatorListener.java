package com.dantesoft.siremono.modules.profile.events;

import com.dantesoft.siremono.modules.auth.events.UserRegisteredEvent;
import com.dantesoft.siremono.modules.profile.store.ProfileData;
import com.dantesoft.siremono.modules.profile.store.ProfileEntity;
import com.dantesoft.siremono.modules.profile.store.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileCreatorListener {
	private final ProfileService service;

	@EventListener
	public void onEvent(UserRegisteredEvent event) {
		log.info("User registered event triggered at {}", event.getTimestamp());

		var user = event.getSource();
		var profile = new ProfileEntity();

		profile.setUser(user);
		profile.setRecentlyCreated(true);
		var data = new ProfileData();
		data.setMenuMode("static");
		data.setPreset("Material");
		data.setPrimary("orange");
		
    profile.setData(data);

		service.save(profile);
	}

}