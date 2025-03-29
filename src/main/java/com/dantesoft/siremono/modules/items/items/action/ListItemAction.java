package com.dantesoft.siremono.modules.items.items.action;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListItemAction extends AbstractAction<ListItemInput, ListItemOutput> {
  private final ItemService itemService;

  @Override
  public ListItemOutput doExecute() {
    final var pageable = getInput().getPageable();
    final var searchParam = getInput().getSearchParam();
    final var filteredSearchParam = Optional.ofNullable(searchParam)
        .filter(param -> !param.isBlank());
    final var out = new ListItemOutput();

    out.setOutput(
        filteredSearchParam
            .map(param -> itemService.allBySearchParam(param, pageable))
            .orElseGet(() -> itemService.all(pageable)));

    return out;
  }

}
