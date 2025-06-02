package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteVariantAttributeDefinitionAction extends AbstractCommand<DeleteVariantAttributeDefinitionInput, DeleteVariantAttributeDefinitionOutput> {
  private final VariantAttributeDefinitionService service;

  @Override
  protected DeleteVariantAttributeDefinitionOutput doExecute() {
    service.deleteById(getInput().id());
    return null;
  }
}
