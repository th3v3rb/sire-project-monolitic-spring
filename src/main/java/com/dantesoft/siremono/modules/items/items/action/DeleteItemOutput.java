package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Schema(
    name = "Delete item output - success",
    description = "Describes the deleted item")
public class DeleteItemOutput implements CommandOutput {
}
