package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.people.people.store.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PersonFindAction extends AbstractCommand<PersonFindInput, PersonFindOutput> {
  private final PersonService personService;

  @Override
  public PersonFindOutput doExecute() {
    var person = personService.findByIdOrFail(getInput().getId());
    var out = new PersonFindOutput();
    out.setData(person);
    return out;
  }


}
