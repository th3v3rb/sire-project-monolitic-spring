package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    name = "Disable many items output - success",
    description = "Contains data about the disable operation. Like the quantity updated rows")
public class DisableManyItemsOutput implements ActionOutputContract {
  @Schema(description = "The quantity of items disabled")
  private int quantityUdated;
}
