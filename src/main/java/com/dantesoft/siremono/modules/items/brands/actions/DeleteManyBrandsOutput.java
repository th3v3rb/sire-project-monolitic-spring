package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(
    name = "Delete many brands output - success",
    description = "The output of the delete many brands action")
@NoArgsConstructor
public class DeleteManyBrandsOutput implements CommandOutput {
	// no output
}
