package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.data.domain.Page;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "List brand output - success",
    description = "Contains the paged list output")
public class ListBrandOutput implements ActionOutputContract {
  @Schema(description = "The paged list")
  private Page<BrandEntity> payload;
}
