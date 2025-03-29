package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DisableCategoryAction extends AbstractAction<DisableCategoryInput, DisableCategoryOutput> {
  private final CategoryService service;

  @Override
  public DisableCategoryOutput doExecute() {
    var id = getInput().getId();
    var out = new DisableCategoryOutput();

    var category = service.findByIdOrFail(id);
    category.setEnabled(false);
    var disabledCategory = service.save(category);

    out.setData(disabledCategory);
    return out;
  }

}
