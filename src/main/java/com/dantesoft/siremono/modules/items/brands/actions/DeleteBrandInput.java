package com.dantesoft.siremono.modules.items.brands.actions;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Delete brand input",
    description = "Requested arguments of the 'Delete brand input' action")
public class DeleteBrandInput implements ActionInputContract {
  @Schema(description = "The id of the brand to delete")
  private UUID id;
}
