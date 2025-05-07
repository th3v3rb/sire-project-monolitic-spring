package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteManyBrandsAction extends AbstractCommand<DeleteManyBrandsInput, DeleteManyBrandsOutput> {
  private final BrandService brandService;

  @Override
  public DeleteManyBrandsOutput doExecute() {
    var ids = getInput().getIds();
    brandService.deleteWhereAllInIds(ids);
    return new DeleteManyBrandsOutput();
  }

}
