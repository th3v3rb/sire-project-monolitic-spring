package com.dantesoft.siremono.modules.people.people.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.people.people.PeopleErrors;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import com.dantesoft.siremono.modules.people.people.store.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonFindAction extends AbstractAction<PersonFindInput, PersonFindOutput> {
  private final PersonService personService;

  @Override
  public PersonFindOutput doExecute() {
    var person = findPerson();
    var out = new PersonFindOutput();
    out.setData(person);
    return out;
  }
  
  private PersonEntity findPerson() {
    var id = getInput().getId();
    return personService.findById(id)
        .orElseThrow(() -> new PeopleErrors.NotFoundException(id));
  }

}
