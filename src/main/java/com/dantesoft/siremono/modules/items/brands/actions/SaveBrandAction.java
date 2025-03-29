package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveBrandAction extends AbstractAction<SaveBrandInput, SaveBrandOutput> {
  private final BrandService brandService;

  @Override
  public SaveBrandOutput doExecute() {
    var brand = new BrandEntity();
    var out = new SaveBrandOutput();

    brand.setName(getInput().getName());
    brand.setEnabled(true);
    var savedBrand = this.brandService.save(brand);
    
    out.setData(savedBrand);
    return out;
  }

}
