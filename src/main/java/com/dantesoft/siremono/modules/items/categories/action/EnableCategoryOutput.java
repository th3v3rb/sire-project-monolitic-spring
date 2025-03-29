package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Enable category output - success",
    description = "DTO output for enabling a category, containing the enabled category entity.")
public class EnableCategoryOutput implements ActionOutputContract {

  @Schema(
      description = "The enabled category",
      implementation = CategoryEntity.class)
  private CategoryEntity data;
}
