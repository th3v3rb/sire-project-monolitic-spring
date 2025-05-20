package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Schema(name = "Save item output - success", description = "Refers to the output of the operation")
public class SaveItemOutput extends AbstractOutput<ItemEntity> {

}
