package com.dantesoft.siremono.modules.items.items.action;

import java.math.BigDecimal;
import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
    name = "Save item input",
    description = "The data necessary for register a item on the system")
public class SaveItemInput implements ActionInputContract {

  @Size(min = 3)
  @NotBlank
  @Schema(description = "The name of the item")
  private String name;

  @Size(min = 5)
  @Schema(
      description = "The description of the item, procedency, another names, etc. Its Optional.")
  private String description;

  @NotNull
  @PositiveOrZero
  @Schema(
      description = "The buy price of the item, put 0 if not has a buy price.")
  private BigDecimal buyPrice;

  @NotNull
  @PositiveOrZero
  @Schema(
      description = "The sell price of the item, put 0 if not has a sell price.")
  private BigDecimal sellPrice;

  @NotNull
  @PositiveOrZero
  @Schema(
      description = "The quantity on the stock, if a service put 0, The minimum quantity is 0.")
  private Long quantityOnStock;

  @Schema(
      description = "The initial status of the item, the default value is true (enabled)")
  private boolean enabled;

  @NotNull
  @Schema(
      description = "The related brand identifier, it's mandatory. Put the id of NO BRAND ID if not has a brand")
  private UUID brandId;

  @NotNull
  @Schema(
      description = "The related category identifier, it's mandatory. Put NO CATEGORY ID if not has a category")
  private UUID categoryId;
}
