package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DisableManyBrandsAction extends AbstractAction<DisableManyBrandsInput, DisableManyBrandsOutput> {
  private final BrandService brandService;

  @Override
  public DisableManyBrandsOutput doExecute() {
    var ids = getInput().getIds();

    var elementChagedQuantity = brandService
        .updateStatusWhereAllInIds(ids, false);

    var out = new DisableManyBrandsOutput();
    out.setQuantity(elementChagedQuantity);
    return out;
  }

}
