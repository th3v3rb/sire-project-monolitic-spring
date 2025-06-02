package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SaveBrandAction extends AbstractCommand<SaveBrandInput, SaveBrandOutput> {
  private final BrandService brandService;

  @Override
  public SaveBrandOutput doExecute() {
    var brand = new BrandEntity();
    var out = new SaveBrandOutput();

    brand.setName(getInput().getName());
    brand.setEnabled(true);
    var savedBrand = this.brandService.save(brand);

    out.setPayload(savedBrand);
    return out;
  }

}
