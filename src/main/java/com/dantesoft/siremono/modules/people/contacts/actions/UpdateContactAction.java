package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;
import com.dantesoft.siremono.modules.people.contacts.store.ContactService;
import com.dantesoft.siremono.modules.people.contacts.store.ContactTypeEntity;
import com.dantesoft.siremono.modules.people.contacts.store.ContactTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UpdateContactAction extends AbstractCommand<UpdateContactInput, UpdateContactOutput> {
  private final ContactService contactService;
  private final ContactTypeService contactTypeService;

  @Override
  public UpdateContactOutput doExecute() {
    var contactType = findContactTypeOrFail();
    var contact = findContact();
    var updatedDescription = getInput().getContact();

    contact.setDescription(updatedDescription);
    contact.setContactType(contactType);

    var updatedContact = contactService.save(contact);

    var out = new UpdateContactOutput();
    out.setContact(updatedContact);
    return out;
  }

  private ContactTypeEntity findContactTypeOrFail() {
    var id = getInput().getContactTypeId();
    return contactTypeService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  private ContactEntity findContact() {
    var id = getInput().getId();
    return contactService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }
}
