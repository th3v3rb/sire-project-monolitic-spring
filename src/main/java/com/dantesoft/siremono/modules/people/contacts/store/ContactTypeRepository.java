package com.dantesoft.siremono.modules.people.contacts.store;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactTypeEntity, UUID> {

}
