package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.people.contacts.store.ContactService;
import com.dantesoft.siremono.modules.people.people.store.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PersonDeleteAction extends AbstractCommand<PersonDeleteInput, PersonDeleteOutput> {
  private final PersonService personService;
  private final ContactService contactService;

  @Override
  public PersonDeleteOutput doExecute() {
    var person = personService.findByIdOrFail(getInput().getId());
    contactService.deleteAll(person.getContacts());
    var deletedPerson = personService.delete(person);
    var out = new PersonDeleteOutput();
    out.setData(deletedPerson);
    return out;
  }

}
