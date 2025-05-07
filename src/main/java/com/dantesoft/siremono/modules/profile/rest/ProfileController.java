package com.dantesoft.siremono.modules.profile.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.profile.actions.ProfileFindAction;
import com.dantesoft.siremono.modules.profile.actions.ProfileFindInput;
import com.dantesoft.siremono.modules.profile.actions.ProfileFindOutput;
import com.dantesoft.siremono.modules.profile.actions.ProfileUpdateAction;
import com.dantesoft.siremono.modules.profile.actions.ProfileUpdateInput;
import com.dantesoft.siremono.modules.profile.actions.ProfileUpdateOutput;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<ProfileFindOutput> getProfile() {
    var input = new ProfileFindInput();
    var out = handler.execute(ProfileFindAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PatchMapping
  public ResponseEntity<ProfileUpdateOutput> update(@RequestBody ProfileUpdateInput input) {
    var out = handler.execute(ProfileUpdateAction.class, input);
    return ResponseEntity.ok(out);
  }
}
