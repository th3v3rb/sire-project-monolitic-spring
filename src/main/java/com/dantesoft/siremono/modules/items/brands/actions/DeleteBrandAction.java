package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteBrandAction extends AbstractAction<DeleteBrandInput, DeleteBrandOutput> {
  private final BrandService brandService;

  @Override
  public DeleteBrandOutput doExecute() {
    var id = getInput().getId();
    var out = new DeleteBrandOutput();

    var brand = brandService.findByIdOrFail(id);
    brandService.delete(brand);
    
    out.setData(brand);
    return out;
  }

}
