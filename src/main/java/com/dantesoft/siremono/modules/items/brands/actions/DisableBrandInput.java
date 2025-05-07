package com.dantesoft.siremono.modules.items.brands.actions;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Disable brand input",
    description = "The requested arguments for the 'Disable brand' action")
public class DisableBrandInput implements CommandInput {
  private UUID id;
}
