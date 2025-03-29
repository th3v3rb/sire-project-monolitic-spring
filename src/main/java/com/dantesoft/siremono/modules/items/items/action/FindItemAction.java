package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindItemAction extends AbstractAction<FIndItemInput, FindItemOutput> {
  private final ItemService itemService;

  @Override
  public FindItemOutput doExecute() {
    var id = getInput().getId();

    var storedItem = itemService.findByIdOrFail(id);

    var out = new FindItemOutput();
    out.setData(storedItem);
    return out;
  }

}
