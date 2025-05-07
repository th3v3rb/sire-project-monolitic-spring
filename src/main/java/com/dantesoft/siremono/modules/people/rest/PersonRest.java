package com.dantesoft.siremono.modules.people.rest;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactAction;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactInput;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactOutput;
import com.dantesoft.siremono.modules.people.contacts.actions.RemoveContactAction;
import com.dantesoft.siremono.modules.people.contacts.actions.RemoveContactInput;
import com.dantesoft.siremono.modules.people.contacts.actions.RemoveContactOutput;
import com.dantesoft.siremono.modules.people.contacts.actions.UpdateContactAction;
import com.dantesoft.siremono.modules.people.contacts.actions.UpdateContactInput;
import com.dantesoft.siremono.modules.people.contacts.actions.UpdateContactOutput;
import com.dantesoft.siremono.modules.people.people.actions.PersonDeleteAction;
import com.dantesoft.siremono.modules.people.people.actions.PersonDeleteInput;
import com.dantesoft.siremono.modules.people.people.actions.PersonDeleteOutput;
import com.dantesoft.siremono.modules.people.people.actions.PersonFindAction;
import com.dantesoft.siremono.modules.people.people.actions.PersonFindInput;
import com.dantesoft.siremono.modules.people.people.actions.PersonFindOutput;
import com.dantesoft.siremono.modules.people.people.actions.PersonListAction;
import com.dantesoft.siremono.modules.people.people.actions.PersonListInput;
import com.dantesoft.siremono.modules.people.people.actions.PersonListOutput;
import com.dantesoft.siremono.modules.people.people.actions.PersonSaveAction;
import com.dantesoft.siremono.modules.people.people.actions.PersonSaveInput;
import com.dantesoft.siremono.modules.people.people.actions.PersonSaveOutput;
import com.dantesoft.siremono.modules.people.people.actions.PersonUpdateAction;
import com.dantesoft.siremono.modules.people.people.actions.PersonUpdateInput;
import com.dantesoft.siremono.modules.people.people.actions.PersonUpdateOutput;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/people")
public class PersonRest {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<PersonListOutput> all(@PageableDefault Pageable pageable,
      @RequestParam(required = false) String searchParam) {
    var input = new PersonListInput();
    input.setPageable(pageable);
    input.setSearchParam(searchParam);
    var out = handler.execute(PersonListAction.class, input);
    return ResponseEntity.ok(out);
  }

  @GetMapping("{id}")
  public ResponseEntity<PersonFindOutput> find(@PathVariable UUID id, @RequestParam PersonFindInput input) {
    input.setId(id);
    var out = handler.execute(PersonFindAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping
  public ResponseEntity<PersonSaveOutput> save(@RequestBody PersonSaveInput input) {
    var out = handler.execute(PersonSaveAction.class, input);
    return ResponseEntity.status(HttpStatus.CREATED).body(out);
  }

  @PostMapping("/contact")
  public ResponseEntity<AttachContactOutput> attachContact(@RequestBody AttachContactInput input) {
    var out = handler.execute(AttachContactAction.class, input);
    return ResponseEntity.status(HttpStatus.CREATED).body(out);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonUpdateOutput> update(@PathVariable UUID id, @RequestBody PersonUpdateInput input) {
    input.setId(id);
    var out = handler.execute(PersonUpdateAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PutMapping("/contact/{id}")
  public ResponseEntity<UpdateContactOutput> updateContact(@PathVariable UUID id,
      @RequestBody UpdateContactInput input) {
    input.setId(id);
    var out = handler.execute(UpdateContactAction.class, input);
    return ResponseEntity.ok(out);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PersonDeleteOutput> delete(@PathVariable UUID id, PersonDeleteInput input) {
    input.setId(id);
    var out = handler.execute(PersonDeleteAction.class, input);
    return ResponseEntity.ok(out);
  }

  @DeleteMapping("/contact/{id}")
  public ResponseEntity<RemoveContactOutput> removeContact(@PathVariable UUID id, RemoveContactInput input) {
    input.setContactId(id);
    var out = handler.execute(RemoveContactAction.class, input);
    return ResponseEntity.ok(out);
  }

}
