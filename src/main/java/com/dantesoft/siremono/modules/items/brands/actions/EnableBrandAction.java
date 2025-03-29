package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnableBrandAction extends AbstractAction<EnableBrandInput, EnableBrandOutput> {
  private final BrandService brandService;

  @Override
  public EnableBrandOutput doExecute() {
    var id = getInput().getId();
    var out = new EnableBrandOutput();

    var entity = brandService.findByIdOrFail(id);
    entity.setEnabled(true);
    var updatedEntity = brandService.save(entity);

    out.setData(updatedEntity);
    return out;
  }

}
