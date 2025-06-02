package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditVariantAttributeDefinitionAction extends AbstractCommand<EditVariantAttributeDefinitionInput, EditVariantAttributeDefinitionOutput> {
  private final VariantAttributeDefinitionService service;

  @Override
  protected EditVariantAttributeDefinitionOutput doExecute() {
    var draft = VariantAttributeDefinitionEntity
        .builder()
        .name(getInput().getName())
        .build();

    draft.setId(getInput().getId());

    var entity = service.save(draft);

    return AbstractOutput.of(EditVariantAttributeDefinitionOutput.class, entity);
  }
}
