package com.dantesoft.siremono.modules.items.items.action;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Delete item input",
    description = "Delete item action arguments")
public class DeleteItemInput implements CommandInput {
  @Schema(description = "The id of the item to delete")
  @NotNull
  private UUID id;
}
