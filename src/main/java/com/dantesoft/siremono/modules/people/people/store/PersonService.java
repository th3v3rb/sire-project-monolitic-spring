package com.dantesoft.siremono.modules.people.people.store;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
  private final PersonRepository personRepo;

  /**
   * 
   * 
   * 
   * @param pageable
   * @return
   */
  public Page<PersonEntity> all(Pageable pageable) {
    return this.personRepo.findAll(pageable);
  }

  /**
   * 
   * @param searchParam PersonEntity social reason
   * @param pageable
   * @return
   */
  public Page<PersonEntity> allBySearchParam(String searchParam, Pageable pageable) {
    return this.personRepo.findBySocialReasonContainingIgnoreCase(searchParam, pageable);
  }

  /**
   * 
   * 
   * 
   * @param person
   * @return
   */
  public PersonEntity save(PersonEntity person) {
    return this.personRepo.save(person);
  }

  /**
   * 
   * 
   * @param id
   * @return
   */
  public Optional<PersonEntity> findById(UUID id) {
    return this.personRepo.findById(id);
  }

  /**
   * 
   * 
   * @param person
   * @return
   */
  public PersonEntity delete(PersonEntity person) {
    this.personRepo.delete(person);
    return person;
  }

}
