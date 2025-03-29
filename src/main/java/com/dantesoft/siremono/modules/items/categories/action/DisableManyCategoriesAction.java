package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DisableManyCategoriesAction extends AbstractAction<DisableManyCategoriesInput, DisableManyCategoriesOutput> {
  private final CategoryService service;

  @Override
  public DisableManyCategoriesOutput doExecute() {
    var ids = getInput().getIds();
    var out = new DisableManyCategoriesOutput();

    var elementChagedQuantity = service.updateStatusWhereAllInIds(ids, false);

    out.setQuantity(elementChagedQuantity);
    return out;
  }

}
