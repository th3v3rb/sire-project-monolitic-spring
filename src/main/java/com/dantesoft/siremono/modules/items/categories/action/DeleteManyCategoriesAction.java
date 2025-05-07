package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteManyCategoriesAction extends AbstractCommand<DeleteManyCategoriesInput, DeleteManyCategoriesOutput> {
  private final CategoryService service;

  @Override
  public DeleteManyCategoriesOutput doExecute() {
    var ids = getInput().getIds();
    service.deleteWhereAllInIds(ids);
    return new DeleteManyCategoriesOutput();
  }

}
