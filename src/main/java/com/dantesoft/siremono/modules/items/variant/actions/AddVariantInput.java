package com.dantesoft.siremono.modules.items.variant.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.modules.items.variant.store.dto.VariantDefinition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class AddVariantInput implements CommandInput {
  @NotBlank
  private String name;
  @NotNull
  private BigDecimal price;
  @NotNull
  private List<VariantDefinition> definition;

  private UUID itemId;
}

