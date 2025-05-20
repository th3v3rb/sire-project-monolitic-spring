package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Delete brand output - success",
    description = "The success output of the 'Delete brand' action")
public class DeleteBrandOutput extends AbstractOutput<BrandEntity> {

}
