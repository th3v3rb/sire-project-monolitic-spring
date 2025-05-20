package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(
  name = "Update item input",
  description = "The input necessary for updating a item")
public class UpdateItemInput implements CommandInput {
  @Schema(
    hidden = true,
    description = "Internal use, is the item id. It is override in the process")
  private UUID id;

  @Schema(
    description = "The new imagen encoded in base64")
  private String image;

  @NotBlank
  @Size(
    min = 3)
  @Schema(
    description = "The new name of the item")
  private String name;
  @Size(
    min = 3)
  @Schema(
    description = "The new description of the item")
  private String description;

  @NotNull
  @PositiveOrZero
  @Schema(
    description = "The item buy price")
  private BigDecimal buyPrice;

  @NotNull
  @PositiveOrZero
  @Schema(
    description = "The item sell price")
  private BigDecimal sellPrice;

  @NotNull
  @Schema(
    description = "The related brand, put NO BRAND ID if not has a brand")
  private UUID brandId;

  @NotNull
  @NotEmpty
  @Schema(
    description = "The related category for the item, put NO CATEGORY ID if not has a category")
  private List<UUID> categories;
}
