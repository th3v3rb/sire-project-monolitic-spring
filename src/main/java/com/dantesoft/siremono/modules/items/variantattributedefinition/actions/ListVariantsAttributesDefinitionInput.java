package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import org.springframework.data.domain.Pageable;

public record ListVariantsAttributesDefinitionInput(
    Pageable pageable,
    String filter
) implements CommandInput {
}
