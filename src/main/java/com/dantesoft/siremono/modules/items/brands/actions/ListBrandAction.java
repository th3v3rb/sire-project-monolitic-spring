package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ListBrandAction extends AbstractCommand<ListBrandInput, ListBrandOutput> {
  private final BrandService brandService;

  @Override
  public ListBrandOutput doExecute() {
    var searchParam = getInput().getSearchParam();
    var pageable = getInput().getPageable();

    var payload = this.brandService.searchBrands(searchParam, pageable);

    var out = new ListBrandOutput();
    out.setPayload(payload);

    return out;
  }

}
