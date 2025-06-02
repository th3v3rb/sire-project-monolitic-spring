package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdateItemInput implements CommandInput {
  private UUID id;

  private String image;

  @NotBlank
  @Size(
      min = 3)
  private String name;
  @Size(
      min = 3)
  private String description;

  @NotNull
  @PositiveOrZero
  private BigDecimal buyPrice;

  @NotNull
  @PositiveOrZero
  private BigDecimal sellPrice;

  @NotNull
  private UUID brandId;

  @NotNull
  @NotEmpty
  private List<UUID> categories;
}
