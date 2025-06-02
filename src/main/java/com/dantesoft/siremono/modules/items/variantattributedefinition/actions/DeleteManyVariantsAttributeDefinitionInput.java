package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record DeleteManyVariantsAttributeDefinitionInput(
    @NotEmpty List<UUID> ids
) implements CommandInput {


}
