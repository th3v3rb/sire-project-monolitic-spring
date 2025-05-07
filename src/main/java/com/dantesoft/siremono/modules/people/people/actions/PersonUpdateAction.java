package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.people.document_types.store.DocumentTypeEntity;
import com.dantesoft.siremono.modules.people.document_types.store.DocumentTypeService;
import com.dantesoft.siremono.modules.people.people.PeopleErrors;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import com.dantesoft.siremono.modules.people.people.store.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PersonUpdateAction extends AbstractCommand<PersonUpdateInput, PersonUpdateOutput> {
  private final PersonService personService;
  private final DocumentTypeService documentTypeService;

  @Override
  public PersonUpdateOutput doExecute() {
    var person = findPerson();
    var documentType = findDocumentType();
    var socialReason = getInput().getSocialReason();
    var document = getInput().getDocument();

    person.setSocialReason(socialReason);
    person.setDocument(document);
    person.setDocumentType(documentType);

    var updatedPerson = personService.save(person);

    var out = new PersonUpdateOutput();
    out.setPerson(updatedPerson);
    return out;

  }

  private PersonEntity findPerson() {
    var id = getInput().getId();
    return personService.findById(id)
        .orElseThrow(() -> new PeopleErrors.NotFoundException(id));
  }

  private DocumentTypeEntity findDocumentType() {
    var id = getInput().getDocumentTypeId();
    return documentTypeService.findById(id).orElseThrow(
        () -> new PeopleErrors.NotFoundException("Not found documentType", id));
  }

}
