package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UpdateCategoryAction extends AbstractCommand<UpdateCategoryInput, UpdateCategoryOutput> {
  private final CategoryService categoryService;

  @Override
  public UpdateCategoryOutput doExecute() {
    var id = getInput().getId();
    var name = getInput().getName();

    var storedBrand = categoryService.findByIdOrFail(id);
    storedBrand.setName(name);
    var updatedBrand = categoryService.save(storedBrand);

    var out = new UpdateCategoryOutput();
    out.setPayload(updatedBrand);
    return out;

  }

}
