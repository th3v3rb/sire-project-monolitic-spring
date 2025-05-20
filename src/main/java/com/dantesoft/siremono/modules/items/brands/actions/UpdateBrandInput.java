package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(name = "Update brand input", description = "Data to update a brand")
public class UpdateBrandInput implements CommandInput {

  @Schema(
      hidden = true,
      description = "Brand ID",
      example = "550e8400-e29b-41d4-a716-446655440000")
  private UUID id;

  @NotBlank
  @Size(min = 3)
  @Schema(description = "Brand name", example = "Adidos")
  private String name;
}
