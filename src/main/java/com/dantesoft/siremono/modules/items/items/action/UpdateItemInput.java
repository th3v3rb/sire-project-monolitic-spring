package com.dantesoft.siremono.modules.items.items.action;

import java.math.BigDecimal;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "Update item input",
    description = "The input necessary for updating a item")
public class UpdateItemInput implements ActionInputContract {
  @Schema(
      hidden = true,
      description = "Internal use, is the item id. It is override in the process")
  private UUID id;

  @NotBlank
  @Size(min = 3)
  @Schema(description = "The new name of the item")
  private String name;
  @Size(min = 3)
  @Schema(description = "The new description of the item")
  private String description;
  @NotBlank
  @PositiveOrZero
  @Schema(description = "The item buy price")
  private BigDecimal buyPrice;
  @NotBlank
  @PositiveOrZero
  @Schema(description = "The item sell price")
  private BigDecimal sellPrice;
  @NotBlank
  @PositiveOrZero
  @Schema(description = "The quantity in the stock")
  private Long quantityOnStock;
  @NotBlank
  @Schema(description = "The related brand, put NO BRAND ID if not has a brand")
  private UUID brandId;
  @NotBlank
  @Schema(
      description = "The related category for the item, put NO CATEGORY ID if not has a category")
  private UUID categoryId;
}
