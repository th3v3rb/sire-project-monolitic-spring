package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Schema(
    name = "Enable category input",
    description = "The necesary arguments to execute the 'Enable category' action")
public class EnableCategoryInput implements CommandInput {
  @Schema(description = "The id of the category to enable")
  private UUID id;
}
