package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Delete many categoreis ouptut - success",
    description = "Response object returned after batch deletion of categories.")
public class DeleteManyCategoriesOutput implements ActionOutputContract {

  @Schema(
      description = "The number of categories successfully deleted.",
      example = "5")
  private int quantity;
}