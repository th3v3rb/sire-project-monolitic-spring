package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteManyBrandsAction extends AbstractAction<DeleteManyBrandsInput, DeleteManyBrandsOutput> {
  private final BrandService brandService;

  @Override
  public DeleteManyBrandsOutput doExecute() {
    var ids = getInput().getIds();

    var elementChagedQuantity = brandService.deleteWhereAllInIds(ids);

    var out = new DeleteManyBrandsOutput();
    out.setQuantity(elementChagedQuantity);
    return out;
  }

}
