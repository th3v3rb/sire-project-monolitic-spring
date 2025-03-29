package com.dantesoft.siremono.modules.items.items.action;

import java.util.List;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    name = "Delete item batch input",
    description = "The body of the delete item on batch request")
public class DeleteManyItemsInput implements ActionInputContract {
  @Schema(description = "Its the list of ids to delete")
  private List<UUID> ids;
}
