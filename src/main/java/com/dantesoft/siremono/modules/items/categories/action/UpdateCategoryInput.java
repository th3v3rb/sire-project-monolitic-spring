package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(
    name = "Update category input",
    description = """
                  DTO input for updating a category, containing the necessary data
                  to update an existing category. The 'id' field is for internal use and is not documented.
                  """)
public class UpdateCategoryInput implements CommandInput {

  @Hidden
  private UUID id;

  @NotBlank
  @Schema(description = "Category name to update", example = "Electronics")
  private String name;
}
