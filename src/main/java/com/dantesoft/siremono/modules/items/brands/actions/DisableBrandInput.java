package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Schema(
    name = "Disable brand input",
    description = "The requested arguments for the 'Disable brand' action")
public class DisableBrandInput implements CommandInput {
  private UUID id;
}
