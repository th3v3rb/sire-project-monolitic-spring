package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    name = "Enable many items output - success",
    description = "Contains information about the operation")
public class EnableManyItemsOutput implements ActionOutputContract {
  @Schema(name = "Quantity of items to be enabled on this operation")
  private int quantityUpdated;
}
