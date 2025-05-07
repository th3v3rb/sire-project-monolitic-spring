package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.people.contacts.ContactErrors;
import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;
import com.dantesoft.siremono.modules.people.contacts.store.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RemoveContactAction extends AbstractCommand<RemoveContactInput, RemoveContactOutput> {
  private final ContactService contactService;

  @Override
  public RemoveContactOutput doExecute() {
    var contact = findContact();
    var removedContact = contactService.delete(contact);
    var out = new RemoveContactOutput();
    out.setData(removedContact);
    return out;
  }

  private ContactEntity findContact() {
    var id = getInput().getContactId();
    return contactService.findById(id)
        .orElseThrow(() -> new ContactErrors.NotFoundException(id));
  }
}
