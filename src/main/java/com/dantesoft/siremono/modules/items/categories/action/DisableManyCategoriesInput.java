package com.dantesoft.siremono.modules.items.categories.action;

import java.util.List;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Disable many categories input",
    description = "DTO input for disabling multiple categories, containing a list of category IDs to disable.")
public class DisableManyCategoriesInput implements ActionInputContract {

  @NotEmpty(message = "The id list must not be empty")
  @NotBlank
  @Size(min = 3)
  @Schema(
      description = "List of ids to disable",
      example = "[\"a3bb189e-8bf9-3888-9912-ace4e6543002\", \"f47ac10b-58cc-4372-a567-0e02b2c3d479\"]")
  private List<UUID> ids;
}
