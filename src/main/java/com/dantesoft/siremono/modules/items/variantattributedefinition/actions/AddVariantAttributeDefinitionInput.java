package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.NotNull;

public record AddVariantAttributeDefinitionInput(
    @NotNull
    String name
)
    implements CommandInput {

}
