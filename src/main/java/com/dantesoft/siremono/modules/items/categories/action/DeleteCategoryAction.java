package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteCategoryAction extends AbstractAction<DeleteCategoryInput, DeleteCategoryOutput> {
  private final CategoryService service;

  @Override
  public DeleteCategoryOutput doExecute() {
    var id = getInput().getId();
    var out = new DeleteCategoryOutput();

    var category = service.findByIdOrFail(id);
    service.delete(category);

    return out;
  }
}
