package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteManyCategoriesAction extends AbstractAction<DeleteManyCategoriesInput, DeleteManyCategoriesOutput> {
  private final CategoryService service;

  @Override
  public DeleteManyCategoriesOutput doExecute() {
    var ids = getInput().getIds();

    var elementChagedQuantity = service.deleteWhereAllInIds(ids);

    var out = new DeleteManyCategoriesOutput();
    out.setQuantity(elementChagedQuantity);
    return out;
  }

}
