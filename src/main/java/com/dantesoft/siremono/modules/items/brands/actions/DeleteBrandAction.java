package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeleteBrandAction extends AbstractCommand<DeleteBrandInput, DeleteBrandOutput> {
  private final BrandService brandService;

  @Override
  public DeleteBrandOutput doExecute() {
    var id = getInput().getId();
    var out = new DeleteBrandOutput();

    var brand = brandService.findByIdOrFail(id);
    brandService.delete(brand);
    
    out.setPayload(brand);
    return out;
  }

}
