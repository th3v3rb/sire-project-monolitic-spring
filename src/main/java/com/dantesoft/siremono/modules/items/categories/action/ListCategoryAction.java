package com.dantesoft.siremono.modules.items.categories.action;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListCategoryAction extends AbstractAction<ListCategoryInput, ListCategoryOutput> {

  private final CategoryService categoryService;

  @Override
  public ListCategoryOutput doExecute() {
    var searchParam = getInput().getSearchParam();
    var pageable = getInput().getPageable();

    var payload = Optional.ofNullable(searchParam)
        .filter(param -> !param.isEmpty())
        .map(param -> categoryService.allBySearchParam(param, pageable))
        .orElse(categoryService.all(pageable));

    var out = new ListCategoryOutput();
    out.setPayload(payload);
    return out;
  }

}
