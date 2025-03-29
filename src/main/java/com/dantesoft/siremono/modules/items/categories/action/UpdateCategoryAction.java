package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateCategoryAction extends AbstractAction<UpdateCategoryInput, UpdateCategoryOutput> {
  private final CategoryService categoryService;

  @Override
  public UpdateCategoryOutput doExecute() {
    var id = getInput().getId();
    var name = getInput().getName();

    var storedBrand = categoryService.findByIdOrFail(id);
    storedBrand.setName(name);
    var updatedBrand = categoryService.save(storedBrand);

    var out = new UpdateCategoryOutput();
    out.setData(updatedBrand);
    return out;

  }

}
