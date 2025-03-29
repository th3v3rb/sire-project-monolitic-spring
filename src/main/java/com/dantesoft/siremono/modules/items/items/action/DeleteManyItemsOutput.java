package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    name = "Delete item batch output - success",
    description = "The delete item response")
public class DeleteManyItemsOutput implements ActionOutputContract {
  @Schema(description = "The quantity of deleted rows on the operation")
  private int deletedRows;
}
