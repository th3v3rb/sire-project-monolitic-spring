package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SaveItemInput implements CommandInput {

  @Nullable
  private String image;

  @Size(min = 3)
  @NotBlank
  private String name;

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
  private List<UUID> categories;
}
