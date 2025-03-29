package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnableManyItemsAction extends AbstractAction<EnableManyItemsInput, EnableManyItemsOutput> {
  private final ItemService service;

  @Override
  public EnableManyItemsOutput doExecute() {
    var ids = getInput().getIds();

    var quatityUpdated = service.updateStatusWhereIdsIn(ids, true);
    var out = new EnableManyItemsOutput();
    out.setQuantityUpdated(quatityUpdated);
    return out;
  }

}
