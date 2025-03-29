package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListBrandAction extends AbstractAction<ListBrandInput, ListBrandOutput> {
  private final BrandService brandService;

  @Override
  public ListBrandOutput doExecute() {
    var searchParam = getInput().getSearchParam();
    var statusParam = getInput().getSearchStatusParam();
    var pageable = getInput().getPageable();

    var out = new ListBrandOutput();
    var payload = this.brandService
        .searchBrands(searchParam, statusParam, pageable);
    out.setPayload(payload);

    return out;
  }

}
