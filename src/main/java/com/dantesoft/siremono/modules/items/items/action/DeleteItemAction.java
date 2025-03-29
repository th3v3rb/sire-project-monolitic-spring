package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteItemAction extends AbstractAction<DeleteItemInput, DeleteItemOutput> {
  private final ItemService itemService;

  @Override
  public DeleteItemOutput doExecute() {
    var id = getInput().getId();

    var storedItem = itemService.findByIdOrFail(id);
    var deletedItem = itemService.delete(storedItem);

    var out = new DeleteItemOutput();
    out.setData(deletedItem);
    return out;
  }

}
