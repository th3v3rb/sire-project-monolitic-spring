package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(
    name = "Delete brand input",
    description = "Requested arguments of the 'Delete brand input' action")
public class DeleteBrandInput implements CommandInput {
  @Schema(description = "The id of the brand to delete")
  private UUID id;
}
