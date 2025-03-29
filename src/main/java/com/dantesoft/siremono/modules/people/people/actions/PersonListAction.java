package com.dantesoft.siremono.modules.people.people.actions;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.people.people.store.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonListAction extends AbstractAction<PersonListInput, PersonListOutput> {
  private final PersonService personService;

  @Override
  public PersonListOutput doExecute() {
    var searchParam = getInput().getSearchParam();
    var pageable = getInput().getPageable();
    var out = new PersonListOutput();

    var data = Optional.ofNullable(searchParam)
        .filter(param -> !param.isEmpty())
        .map(param -> personService.allBySearchParam(param, pageable))
        .orElse(personService.all(pageable));

    out.setOutput(data);
    return out;
  }

}
