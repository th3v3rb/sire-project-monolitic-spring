package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Save brand input",
    description = "Mandatory data for the save action")
public class SaveBrandInput implements CommandInput {

  @Schema(
      description = "Brand name",
      examples = { "Nike", "Adidas", "Chorti", "Alemana", "Apple" })
  @NotBlank(message = "The name its mandatory")
  private String name;
}
