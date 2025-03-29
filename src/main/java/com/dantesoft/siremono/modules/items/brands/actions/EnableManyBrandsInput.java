package com.dantesoft.siremono.modules.items.brands.actions;

import java.util.List;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Enable many brands input",
    description = "Request payload to enable multiple brands")
public class EnableManyBrandsInput implements ActionInputContract {

  @NotEmpty
  @ArraySchema(
      schema = @Schema(
          type = "string",
          format = "uuid",
          description = "A list of brand IDs to be enabled. Each ID must be a valid UUID.",
          example = "[\"550e8400-e29b-41d4-a716-446655440000\", \"6b1d9f75-d1c7-4c9a-9f36-b7b7f5d11211\"]"))
  @NotNull
  @Size(min = 2)
  private List<UUID> ids;
}
