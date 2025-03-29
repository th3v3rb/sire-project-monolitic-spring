package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Save category input",
    description = "DTO input for saving a category, containing the necessary data to create a new category.")
public class SaveCategoryInput implements ActionInputContract {

  @NotBlank(message = "The name must be not null")
  @Schema(
      description = "Category name",
      example = "ONe cageory",
      required = true)
  private String name;
}
