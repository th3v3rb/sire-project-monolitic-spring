package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveCategoryAction extends AbstractAction<SaveCategoryInput, SaveCategoryOutput> {
  private final CategoryService categoryService;

  @Override
  public SaveCategoryOutput doExecute() {
    var name = getInput().getName();
    var out = new SaveCategoryOutput();

    var category = new CategoryEntity();
    category.setName(name);
    var savedCategory = this.categoryService.save(category);

    out.setData(savedCategory);
    return out;
  }

}
