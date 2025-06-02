package com.dantesoft.siremono.modules.people._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.people.contacts.actions.*;
import com.dantesoft.siremono.modules.people.people.actions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
