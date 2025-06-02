package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddVariantAttributeDefinitionAction extends AbstractCommand<AddVariantAttributeDefinitionInput, AddVariantAttributeDefinitionOutput> {
  private final VariantAttributeDefinitionService variantAttributeDefinitionService;

  @Override
  protected AddVariantAttributeDefinitionOutput doExecute() {
    var draft = VariantAttributeDefinitionEntity
        .builder()
        .name(getInput().name())
        .build();

    var entity = variantAttributeDefinitionService.save(draft);

    return AbstractOutput.of(AddVariantAttributeDefinitionOutput.class, entity);
  }
}
