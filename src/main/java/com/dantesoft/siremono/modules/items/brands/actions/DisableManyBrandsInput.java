package com.dantesoft.siremono.modules.items.brands.actions;

import java.util.List;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Disable many brands input",
    description = "Request payload to disable multiple brands")
public class DisableManyBrandsInput implements ActionInputContract {

  @NotBlank
  @Size(min = 2, message = "At least 2 brand IDs must be provided")
  @ArraySchema(
      schema = @Schema(
          type = "string",
          format = "uuid",
          description = "A list of brand IDs to be disabled. Each ID must be a valid UUID.",
          example = "[\"d290f1ee-6c54-4b01-90e6-d701748f0851\", \"3fa85f64-5717-4562-b3fc-2c963f66afa6\", \"123e4567-e89b-12d3-a456-426614174000\"]"))
  private List<UUID> ids;
}
