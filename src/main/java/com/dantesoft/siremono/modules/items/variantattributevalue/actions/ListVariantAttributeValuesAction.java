package com.dantesoft.siremono.modules.items.variantattributevalue.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListVariantAttributeValuesAction extends AbstractCommand<ListVariantAttributeValuesInput, ListVariantAttributeValuesOutput> {
  private final VariantAttributeValueService variantAttributeValueService;
  private final VariantAttributeDefinitionService variantAttributeDefinitionService;

  @Override
  protected ListVariantAttributeValuesOutput doExecute() {
    return AbstractOutput.of(
        ListVariantAttributeValuesOutput.class,
        variantAttributeValueService.findAllByDefinition(
            variantAttributeDefinitionService.findByIdOrFail(getInput().attributeId()),
            getInput().pageable()
        )
    );
  }
}
