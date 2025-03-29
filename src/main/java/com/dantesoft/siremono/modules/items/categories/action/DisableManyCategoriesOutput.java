package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Disable many categories output - success",
    description = "DTO output for disabling multiple categories, containing the quantity of categories disabled.")
public class DisableManyCategoriesOutput implements ActionOutputContract {

  @Schema(description = "The quantity of disabled categores", example = "3")
  private int quantity;
}
