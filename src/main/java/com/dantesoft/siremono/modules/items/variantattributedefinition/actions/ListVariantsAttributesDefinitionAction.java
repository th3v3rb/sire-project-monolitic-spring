package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListVariantsAttributesDefinitionAction extends AbstractCommand<ListVariantsAttributesDefinitionInput, ListVariantsAttributesDefinitionOutput> {
  private final VariantAttributeDefinitionService service;

  @Override
  protected ListVariantsAttributesDefinitionOutput doExecute() {

    var payload = service.findAllByName(
        getInput().filter(),
        getInput().pageable()
    );

    return AbstractOutput.of(ListVariantsAttributesDefinitionOutput.class, payload);
  }
}
