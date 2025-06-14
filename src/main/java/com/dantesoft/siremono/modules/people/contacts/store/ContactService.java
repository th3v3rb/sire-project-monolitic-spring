package com.dantesoft.siremono.modules.people.contacts.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {
  private ContactRepository contactRepository;

  /**
   * @param id
   * @return
   */
  public Optional<ContactEntity> findById(UUID id) {
    return this.contactRepository.findById(id);
  }

  public ContactEntity save(ContactEntity contact) {
    return this.contactRepository.save(contact);
  }

  public void deleteAll(Iterable<ContactEntity> contactList) {
    this.contactRepository.deleteAll(contactList);
  }

  public ContactEntity delete(ContactEntity contact) {
    this.contactRepository.delete(contact);
    return contact;
  }
}
