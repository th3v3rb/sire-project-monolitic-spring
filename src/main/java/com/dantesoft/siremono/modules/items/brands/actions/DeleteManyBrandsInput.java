package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class DeleteManyBrandsInput implements CommandInput {

  @NotEmpty(message = "The list of brand IDs cannot be empty")
  @NotNull
  private List<UUID> ids;
}
