package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactAction;
import com.dantesoft.siremono.modules.people.document_types.store.DocumentTypeService;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import com.dantesoft.siremono.modules.people.people.store.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PersonSaveAction extends AbstractCommand<PersonSaveInput, PersonSaveOutput> {
  private final PersonService personService;
  private final DocumentTypeService documentTypeService;
  private final CommandExecutor handler;

  @Override
  public PersonSaveOutput doExecute() {
    var documentType = documentTypeService.findByIdOrFail(getInput().getDocumentTypeId());
    var person = new PersonEntity();
    var document = getInput().getDocument();
    var socialReason = getInput().getSocialReason();

    person.setDocument(document);
    person.setSocialReason(socialReason);
    person.setDocumentType(documentType);

    var storedPerson = personService.save(person);
    handler.execute(AttachContactAction.class, getInput().getContactInput());

    var out = new PersonSaveOutput();
    out.setData(storedPerson);
    return out;
  }

}
