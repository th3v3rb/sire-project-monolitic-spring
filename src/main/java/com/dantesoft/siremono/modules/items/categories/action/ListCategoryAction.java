package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ListCategoryAction extends AbstractCommand<ListCategoryInput, ListCategoryOutput> {

  private final CategoryService categoryService;

  @Override
  public ListCategoryOutput doExecute() {
    var searchParam = getInput().getSearchParam();
    var pageable = getInput().getPageable();

    var payload = categoryService.findByNameContainingIgnoreCase(searchParam, pageable);
    var out = new ListCategoryOutput();
    out.setPayload(payload);
    return out;
  }

}
