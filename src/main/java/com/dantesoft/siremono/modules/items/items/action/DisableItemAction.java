package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DisableItemAction extends AbstractAction<DisableItemInput, DisableItemOutput> {
  private ItemService itemService;

  @Override
  public DisableItemOutput doExecute() {
    var id = getInput().getId();
    var item = itemService.findByIdOrFail(id);

    item.setEnabled(false);
    itemService.save(item);

    var out = new DisableItemOutput();
    return out;
  }

}
