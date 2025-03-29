package com.dantesoft.siremono.modules.items.items.action;

import java.util.List;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Enable many items input", description = "Contains the information about the items to be disabled")
public class EnableManyItemsInput implements ActionInputContract {
  @Schema(description = "The list of items ids to be disabled")
  private List<UUID> ids;
}
