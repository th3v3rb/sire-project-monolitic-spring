package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DisableBrandAction extends AbstractCommand<DisableBrandInput, DisableBrandOutput> {
  private final BrandService brandService;

  @Override
  public DisableBrandOutput doExecute() {
    var id = getInput().getId();
    var out = new DisableBrandOutput();

    var entity = brandService.findByIdOrFail(id);
    entity.setEnabled(false);
    var updatedEntity = brandService.save(entity);

    out.setPayload(updatedEntity);
    return out;
  }

}
