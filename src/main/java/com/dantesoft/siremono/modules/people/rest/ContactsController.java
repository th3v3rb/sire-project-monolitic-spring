
package com.dantesoft.siremono.modules.people.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dantesoft.siremono.internal.actions.io.AbstractRest;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactAction;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactInput;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactOutput;

@RestController
@RequestMapping("/api/v1/contact/types")
public class ContactsController extends AbstractRest {

  @PostMapping
  public ResponseEntity<AttachContactOutput> attach(
      @RequestBody AttachContactInput input) {
    return handle(AttachContactAction.class, input);
  }
}
