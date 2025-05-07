package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EnableCategoryAction extends AbstractCommand<EnableCategoryInput, EnableCategoryOutput> {
  private final CategoryService service;

  @Override
  public EnableCategoryOutput doExecute() {
    var id = getInput().getId();
    var out = new EnableCategoryOutput();

    var category = service.findByIdOrFail(id);
    category.setEnabled(true);
    var enabledCategory = service.save(category);

    out.setPayload(enabledCategory);
    return out;
  }

}
