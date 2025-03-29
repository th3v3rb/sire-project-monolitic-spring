package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class DisableBrandAction extends AbstractAction<DisableBrandInput, DisableBrandOutput> {
  private final BrandService brandService;

  @Override
  public DisableBrandOutput doExecute() {
    var id = getInput().getId();
    var out = new DisableBrandOutput();

    var entity = brandService.findByIdOrFail(id);
    entity.setEnabled(false);
    var updatedEntity = brandService.save(entity);

    out.setData(updatedEntity);
    return out;
  }

}
