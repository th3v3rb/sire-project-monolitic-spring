package com.dantesoft.siremono.modules.items.variant.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public record ListVariantsInput(
    String filter,
    UUID itemId,
    Pageable pageable
) implements CommandInput {
}
