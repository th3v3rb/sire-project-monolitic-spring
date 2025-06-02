package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;


import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteManyVariantsAttributeDefinitionAction extends AbstractCommand<DeleteManyVariantsAttributeDefinitionInput, DeleteManyVariantsAttributeDefinitionOutput> {
  private final VariantAttributeDefinitionService variantAttributeDefinitionService;

  @Override
  protected DeleteManyVariantsAttributeDefinitionOutput doExecute() {
    variantAttributeDefinitionService.deleteAllById(getInput().ids());

    return null;
  }
}
