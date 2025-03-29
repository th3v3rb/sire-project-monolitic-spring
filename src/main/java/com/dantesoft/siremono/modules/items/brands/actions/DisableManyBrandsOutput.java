package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(
    name = "Disable many brands output - success",
    description = "The output of the disable many brands action")
@NoArgsConstructor
public class DisableManyBrandsOutput implements ActionOutputContract {
  @Schema(description = "Quantity of disabled brands")
  private int quantity;
}
