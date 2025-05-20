package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Save brand output - success",
    description = "The result is the output of the 'Save brand' action")
public class SaveBrandOutput extends AbstractOutput<BrandEntity>{

}
