package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Schema(name = "Update item output - success", description = "The result of the operation of update a item")
public class UpdateItemOutput extends AbstractOutput<ItemEntity> {
}
