package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Update item output - success",
    description = "The result of the operation of update a item")
public class UpdateItemOutput implements ActionOutputContract {
  @Schema(description = "The updated item information")
  private ItemEntity data;
}
