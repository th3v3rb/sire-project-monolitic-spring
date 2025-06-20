package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeleteCategoryAction extends AbstractCommand<DeleteCategoryInput, DeleteCategoryOutput> {
  private final CategoryService service;
  private final ItemService itemService;

  @Override
  public DeleteCategoryOutput doExecute() {
    var id = getInput().getId();

    var category = service.findByIdOrFail(id);

    var inUse = itemService.existByCategoryContaining(category);

    if (inUse) {
      throw new IllegalStateException("Category is in use: " + category);
    }

    service.delete(category);

    return new DeleteCategoryOutput();
  }
}
