package com.dantesoft.siremono.modules.people.people.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.people.contacts.store.ContactService;
import com.dantesoft.siremono.modules.people.people.PeopleErrors;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import com.dantesoft.siremono.modules.people.people.store.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonDeleteAction extends AbstractAction<PersonDeleteInput, PersonDeleteOutput> {
  private final PersonService personService;
  private final ContactService contactService;

  @Override
  public PersonDeleteOutput doExecute() {
    var person = findPerson();
    contactService.deleteAll(person.getContacts());
    var deletedPerson = personService.delete(person);
    var out = new PersonDeleteOutput();
    out.setData(deletedPerson);
    return out;
  }
  
  private PersonEntity findPerson() {
    var id = getInput().getId();
    return personService.findById(id)
        .orElseThrow(() -> new PeopleErrors.NotFoundException(id));
  }

}
