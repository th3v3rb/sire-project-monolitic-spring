package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnableManyBrandsAction extends AbstractAction<EnableManyBrandsInput, EnableManyBrandsOutput> {
  private final BrandService brandService;

  @Override
  public EnableManyBrandsOutput doExecute() {
    var ids = getInput().getIds();

    var elementChagedQuantity = brandService
        .updateStatusWhereAllInIds(ids, true);

    var out = new EnableManyBrandsOutput();
    out.setQuantity(elementChagedQuantity);
    return out;
  }

}
