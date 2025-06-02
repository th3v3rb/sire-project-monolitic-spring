package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnableVariantAttributeDefinitionAction extends AbstractCommand<EnableVariantAttributeDefinitionInput, EnableVariantAttributeDefinitionOutput> {
  private final VariantAttributeDefinitionService service;

  @Override
  protected EnableVariantAttributeDefinitionOutput doExecute() {
    var draft = service.findByIdOrFail(getInput().id());
    draft.setEnabled(true);
    var entity = service.save(draft);

    return AbstractOutput.of(EnableVariantAttributeDefinitionOutput.class, entity);
  }
}
