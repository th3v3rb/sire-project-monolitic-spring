package com.dantesoft.siremono.modules.items.categories.action;

import java.util.List;
import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Delete many categories input",
    description = "DTO input for deleting multiple categories, containing a list of category IDs to delete.")
public class DeleteManyCategoriesInput implements CommandInput {

  @NotEmpty
  @NotNull
  @Schema(
      description = "The list of ids to delete",
      example = "[\"a3bb189e-8bf9-3888-9912-ace4e6543002\", \"f47ac10b-58cc-4372-a567-0e02b2c3d479\"]")
  private List<UUID> ids;
}
