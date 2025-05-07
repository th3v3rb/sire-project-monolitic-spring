package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UpdateBrandAction extends AbstractCommand<UpdateBrandInput, UpdateBrandOutput> {
  private final BrandService brandService;

  @Override
  public UpdateBrandOutput doExecute() {
    var id = getInput().getId();
    var name = getInput().getName();
    var out = new UpdateBrandOutput();

    var brand = this.brandService.findByIdOrFail(id);
    brand.setName(name);
    var updatedBrand = this.brandService.save(brand);

    out.setPayload(updatedBrand);
    return out;
  }
}
