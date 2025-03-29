package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Update brand output - success",
    description = "Response for updating a brand")
public class UpdateBrandOutput implements ActionOutputContract {
  @Schema(description = "Updated data")
  private BrandEntity data;
}
