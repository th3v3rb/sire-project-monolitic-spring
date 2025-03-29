package com.dantesoft.siremono.modules.items.items.action;

import java.util.List;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    name = "Disable many items input",
    description = "Contains information of the ids of the items to be disabled")
public class DisableManyItemsInput implements ActionInputContract {
  @Schema(description = "Its the list of items ids to be disabled")
  private List<UUID> ids;
}
