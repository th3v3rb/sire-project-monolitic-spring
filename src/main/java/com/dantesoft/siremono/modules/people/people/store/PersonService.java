package com.dantesoft.siremono.modules.people.people.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
  private final PersonRepository personRepo;

  public Page<PersonEntity> all(Pageable pageable) {
    return this.personRepo.findAll(pageable);
  }

  public Page<PersonEntity> allBySearchParam(String searchParam, Pageable pageable) {
    return this.personRepo.findBySocialReasonContainingIgnoreCase(searchParam, pageable);
  }

  public PersonEntity save(PersonEntity person) {
    return this.personRepo.save(person);
  }

  public Optional<PersonEntity> findById(UUID id) {
    return this.personRepo.findById(id);
  }

  public PersonEntity delete(PersonEntity person) {
    this.personRepo.delete(person);
    return person;
  }

  public PersonEntity findByIdOrFail(UUID personId) {
    return personRepo
        .findById(personId)
        .orElseThrow(() -> new EntityNotFoundException(personId.toString()));
  }
}
