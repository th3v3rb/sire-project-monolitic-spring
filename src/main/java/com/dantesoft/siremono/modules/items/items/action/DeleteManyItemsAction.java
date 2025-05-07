package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteManyItemsAction
    extends AbstractCommand<DeleteManyItemsInput, DeleteManyItemsOutput> {
  private final ItemService service;

  @Override
  public DeleteManyItemsOutput doExecute() {
    var ids = getInput().getIds();
    var items = service.findAllById(ids);

    service.deleteAll(items);

    return new DeleteManyItemsOutput();
  }

}
