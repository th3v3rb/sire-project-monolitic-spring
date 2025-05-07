package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.people.contacts.ContactErrors;
import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;
import com.dantesoft.siremono.modules.people.contacts.store.ContactService;
import com.dantesoft.siremono.modules.people.contacts.store.ContactTypeEntity;
import com.dantesoft.siremono.modules.people.contacts.store.ContactTypeService;
import com.dantesoft.siremono.modules.people.people.PeopleErrors;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import com.dantesoft.siremono.modules.people.people.store.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AttachContactAction extends AbstractCommand<AttachContactInput, AttachContactOutput> {
  private final ContactService contactService;
  private final ContactTypeService contactTypeService;
  private final PersonService personService;

  @Override
  public AttachContactOutput doExecute() {
    var contact = new ContactEntity();
    var contactType = findContactType();
    var person = findPerson();

    contact.setDescription(getInput().getDescription());
    contact.setContactType(contactType);
    contact.setPerson(person);

    var storedContact = contactService.save(contact);

    var out = new AttachContactOutput();
    out.setData(storedContact);
    return out;
  }

  private ContactTypeEntity findContactType() {
    var id = getInput().getContactTypeId();
    return contactTypeService.findById(id)
        .orElseThrow(() -> new ContactErrors.NotFoundException(id));
  }

  private PersonEntity findPerson() {
    var id = getInput().getPersonId();
    return personService.findById(id)
        .orElseThrow(() -> new PeopleErrors.NotFoundException(id));
  }
}
