package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Enable many categories output - success",
    description = "DTO output for enabling multiple categories, containing the quantity of categories enabled.")
public class EnableManyCategoriesOutput implements ActionOutputContract {

  @Schema(description = "Quantity enabled", example = "5")
  private int quantity;
}