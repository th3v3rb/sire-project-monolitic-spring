package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SaveCategoryAction extends AbstractCommand<SaveCategoryInput, SaveCategoryOutput> {
  private final CategoryService categoryService;

  @Override
  public SaveCategoryOutput doExecute() {
    var name = getInput().getName();
    var out = new SaveCategoryOutput();

    var category = new CategoryEntity();
    category.setName(name);
    category.setEnabled(true);
    var savedCategory = this.categoryService.save(category);

    out.setPayload(savedCategory);
    return out;
  }

}
