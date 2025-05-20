package com.dantesoft.siremono.modules.profile.rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.profile.actions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
