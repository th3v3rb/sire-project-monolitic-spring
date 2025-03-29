package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Update category output - success",
    description = "DTO output for updating a category, containing the updated category entity.")
public class UpdateCategoryOutput implements ActionOutputContract {

  @Schema(
      description = "Updated category",
      implementation = CategoryEntity.class)
  private CategoryEntity data;
}
