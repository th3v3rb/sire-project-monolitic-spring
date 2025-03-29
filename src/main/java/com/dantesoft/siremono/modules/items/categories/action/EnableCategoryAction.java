package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnableCategoryAction extends AbstractAction<EnableCategoryInput, EnableCategoryOutput> {
  private final CategoryService service;

  @Override
  public EnableCategoryOutput doExecute() {
    var id = getInput().getId();
    var out = new EnableCategoryOutput();

    var category = service.findByIdOrFail(id);
    category.setEnabled(true);
    var enabledCategory = service.save(category);

    out.setData(enabledCategory);
    return out;
  }

}
