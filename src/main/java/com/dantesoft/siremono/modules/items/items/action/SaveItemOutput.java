package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Save item output - success",
    description = "Refers to the output of the operation")
public class SaveItemOutput implements ActionOutputContract {

  @Schema(description = "The new Item")
  private ItemEntity data;
}
