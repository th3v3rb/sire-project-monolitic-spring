package com.dantesoft.siremono.modules.items.variant.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.variant.store.dto.VariantDTO;
import org.springframework.data.domain.Page;

public class ListVariantsOutput extends AbstractOutput<Page<VariantDTO>> {
}
