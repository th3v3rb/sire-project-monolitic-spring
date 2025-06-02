package com.dantesoft.siremono.modules.items.variant.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.variant.store.VariantService;
import com.dantesoft.siremono.modules.items.variant.store.dto.VariantDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListVariantsAction extends AbstractCommand<ListVariantsInput, ListVariantsOutput> {
  private final VariantService variantService;
  private final ItemService itemService;

  @Override
  protected ListVariantsOutput doExecute() {
    var pageable = getInput().pageable();
    var filter = getInput().filter();
    var itemId = getInput().itemId();

    var item = itemService.findByIdOrFail(itemId);

    var page = variantService.list(pageable, filter, item);

    var payload = page.map(e -> VariantDTO.builder()
        .name(e.getName())
        .enabled(e.isEnabled())
        .updatedAt(e.getUpdatedAt())
        .createdAt(e.getCreatedAt())
        .price(e.getPrice())
        .quantity(e.getQuantity())
        .build());

    return AbstractOutput.of(ListVariantsOutput.class, payload);
  }
}
