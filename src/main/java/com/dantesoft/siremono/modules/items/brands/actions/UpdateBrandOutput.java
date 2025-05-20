package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(
    name = "Update brand output - success",
    description = "Response for updating a brand")
public class UpdateBrandOutput extends AbstractOutput<BrandEntity> {
}
