package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;

import java.util.UUID;

public record DisableVariantAttributeDefinitionInput(
    UUID id
) implements CommandInput {
}
