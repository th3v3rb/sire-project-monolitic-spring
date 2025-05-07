package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeleteItemAction extends AbstractCommand<DeleteItemInput, DeleteItemOutput> {
  private final ItemService itemService;

  @Override
  public DeleteItemOutput doExecute() {
    var id = getInput().getId();
    var item = itemService.findByIdOrFail(id);

    itemService.delete(item);
    return new DeleteItemOutput();
  }

}
