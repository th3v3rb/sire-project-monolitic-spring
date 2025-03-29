package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateBrandAction extends AbstractAction<UpdateBrandInput, UpdateBrandOutput> {
  private final BrandService brandService;

  @Override
  public UpdateBrandOutput doExecute() {
    var id = getInput().getId();
    var name = getInput().getName();
    var out = new UpdateBrandOutput();

    var brand = this.brandService.findByIdOrFail(id);
    brand.setName(name);
    var updatedBrand = this.brandService.save(brand);

    out.setData(updatedBrand);
    return out;
  }

}
