package com.dantesoft.siremono.modules.items.variantattributevalue.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public record ListVariantAttributeValuesInput (
    UUID attributeId,
    Pageable pageable
) implements CommandInput { }
