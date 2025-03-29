package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteManyItemsAction extends AbstractAction<DeleteManyItemsInput, DeleteManyItemsOutput> {
  private final ItemService service;

  @Override
  public DeleteManyItemsOutput doExecute() {
    var ids = getInput().getIds();

    var deletedItems = service.deleteAllByIds(ids);

    var out = new DeleteManyItemsOutput();
    out.setDeletedRows(deletedItems);
    return out;
  }

}
