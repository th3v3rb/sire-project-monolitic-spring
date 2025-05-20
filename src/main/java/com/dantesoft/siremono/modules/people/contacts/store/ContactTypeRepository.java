package com.dantesoft.siremono.modules.people.contacts.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactTypeEntity, UUID> {

}
