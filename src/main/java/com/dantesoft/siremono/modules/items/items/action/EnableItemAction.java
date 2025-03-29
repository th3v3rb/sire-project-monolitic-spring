package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnableItemAction extends AbstractAction<EnableItemInput, EnableItemOutput> {
  private final ItemService itemService;

  @Override
  public EnableItemOutput doExecute() {
    var id = getInput().getId();

    var item = itemService.findByIdOrFail(id);
    item.setEnabled(true);
    itemService.save(item);

    var out = new EnableItemOutput();
    return out;
  }

}
