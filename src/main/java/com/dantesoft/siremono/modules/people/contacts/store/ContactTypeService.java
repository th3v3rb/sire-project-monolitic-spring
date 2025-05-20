package com.dantesoft.siremono.modules.people.contacts.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeService {
  private final ContactTypeRepository contactTypeRepository;

  /**
   * @param pageable
   * @return
   */
  public Page<ContactTypeEntity> all(Pageable pageable) {
    return contactTypeRepository.findAll(pageable);
  }

  /**
   * @param contactType
   * @return
   */
  public ContactTypeEntity save(ContactTypeEntity contactType) {
    return this.contactTypeRepository.save(contactType);
  }

  /**
   * @param id
   * @return
   */
  public Optional<ContactTypeEntity> findById(UUID id) {
    return this.contactTypeRepository.findById(id);
  }

  public ContactTypeEntity findByIdOrFail(UUID id) {
    return contactTypeRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  /**
   * @param contactType
   * @return
   */
  public ContactTypeEntity delete(ContactTypeEntity contactType) {
    this.contactTypeRepository.delete(contactType);
    return contactType;
  }
}
