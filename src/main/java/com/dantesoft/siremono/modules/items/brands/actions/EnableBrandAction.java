package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EnableBrandAction extends AbstractCommand<EnableBrandInput, EnableBrandOutput> {
  private final BrandService brandService;

  @Override
  public EnableBrandOutput doExecute() {
    var id = getInput().getId();
    var out = new EnableBrandOutput();

    var entity = brandService.findByIdOrFail(id);
    entity.setEnabled(true);
    var updatedEntity = brandService.save(entity);

    out.setPayload(updatedEntity);
    return out;
  }

}
