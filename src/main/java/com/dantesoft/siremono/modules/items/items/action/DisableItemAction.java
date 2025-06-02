package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DisableItemAction extends AbstractCommand<DisableItemInput, DisableItemOutput> {
  private final ItemService itemService;

  @Override
  public DisableItemOutput doExecute() {
    var id = getInput().getId();
    var item = itemService.findByIdOrFail(id);
    item.setEnabled(false);
    itemService.save(item);
    return new DisableItemOutput();
  }

}
