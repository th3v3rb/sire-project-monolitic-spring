package com.dantesoft.siremono.modules.items.variant.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.variant.store.VariantEntity;
import com.dantesoft.siremono.modules.items.variant.store.VariantService;
import com.dantesoft.siremono.modules.items.variant.store.dto.VariantDTO;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueEntity;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddVariantAction extends AbstractCommand<AddVariantInput, AddVariantOutput> {
  private final VariantService variantService;
  private final ItemService itemService;
  private final VariantAttributeValueService variantAttributeValueService;

  @Override
  protected AddVariantOutput doExecute() {
    var itemId = getInput().getItemId();

    // save variant
    var item = itemService.findByIdOrFail(itemId);

    var draft = VariantEntity.builder()
        .item(item)
        .name(getInput().getName())
        .price(getInput().getPrice())
        .quantity(0)
        .build();

    var entity = variantService.save(draft);

    // save attribute definition value
    getInput().getDefinition().forEach(e -> {
      var d = new VariantAttributeValueEntity();
      d.setVariant(entity);
      d.setValue(e.value());
      d.setDefinition(e.definition());
      variantAttributeValueService.save(d);
    });

    var payload = VariantDTO.builder()
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .enabled(true)
        .name(entity.getName())
        .price(entity.getPrice())
        .quantity(entity.getQuantity())
        .build();

    return AbstractOutput.of(AddVariantOutput.class, payload);
  }
}
