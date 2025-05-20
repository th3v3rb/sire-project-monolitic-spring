
package com.dantesoft.siremono.modules.people.rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactAction;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactInput;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact/types")
@RequiredArgsConstructor
public class ContactsController  {
	private final CommandExecutor handler;

  @PostMapping
  public ResponseEntity<AttachContactOutput> attach(
      @RequestBody AttachContactInput input) {
    var out = handler.execute(AttachContactAction.class, input);
    return ResponseEntity.ok(out);
  }
}
