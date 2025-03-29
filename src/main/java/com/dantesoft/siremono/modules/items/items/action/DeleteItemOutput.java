package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Delete item output - success",
    description = "Describes the deleted item")
public class DeleteItemOutput implements ActionOutputContract {
  @Schema(description = "Its the deleted item on the database")
  private ItemEntity data;
}
