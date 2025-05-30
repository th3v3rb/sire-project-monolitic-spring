package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Schema(
    name = "Enable item input",
    description = "The necessary data for enable a item by the 'Enable item' action")
public class EnableItemInput implements CommandInput {
  @Schema(description = "The id of the item to enable")
  private UUID id;
}
