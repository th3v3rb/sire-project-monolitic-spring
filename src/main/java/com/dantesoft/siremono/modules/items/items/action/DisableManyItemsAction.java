package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DisableManyItemsAction extends AbstractAction<DisableManyItemsInput, DisableManyItemsOutput> {
  private final ItemService service;

  @Override
  public DisableManyItemsOutput doExecute() {
    var ids = getInput().getIds();

    var updatedItems = service.updateStatusWhereIdsIn(ids, false);

    var out = new DisableManyItemsOutput();
    out.setQuantityUdated(updatedItems);
    return out;
  }
}
