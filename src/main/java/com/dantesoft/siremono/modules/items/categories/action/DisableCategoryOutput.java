package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Disable category output - success",
    description = "DTO output for disabling a category, containing the disabled category entity.")
public class DisableCategoryOutput implements ActionOutputContract {

  @Schema(
      description = "The entity disabled",
      implementation = CategoryEntity.class)
  private CategoryEntity data;
}
