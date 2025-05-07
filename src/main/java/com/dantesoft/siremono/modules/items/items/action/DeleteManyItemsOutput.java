package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Delete item batch output - success", description = "The delete item response")
public class DeleteManyItemsOutput implements CommandOutput {
}
