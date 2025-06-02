package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DisableVariantAttributeDefinitionAction extends AbstractCommand<DisableVariantAttributeDefinitionInput, DisableVariantAttributeDefinitionOutput> {
  private final VariantAttributeDefinitionService service;

  @Override
  protected DisableVariantAttributeDefinitionOutput doExecute() {
    var draft = service.findByIdOrFail(getInput().id());
    draft.setEnabled(false);
    var entity = service.save(draft);

    return AbstractOutput.of(DisableVariantAttributeDefinitionOutput.class, entity);
  }
}
