package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Enable brand output - success",
    description = "The output of the 'Enable brand' action")
public class EnableBrandOutput implements ActionOutputContract {
  private BrandEntity data;
}
