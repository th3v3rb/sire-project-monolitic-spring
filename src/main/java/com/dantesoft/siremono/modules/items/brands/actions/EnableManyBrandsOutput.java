package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Enable many brands output - success",
    description = "Success output of the action 'Enable many brands' ")
public class EnableManyBrandsOutput implements ActionOutputContract {
  @Schema(description = "The quantity of the affected brands")
  private int quantity;
}
