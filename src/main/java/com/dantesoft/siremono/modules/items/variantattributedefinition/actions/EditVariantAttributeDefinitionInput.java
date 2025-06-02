package com.dantesoft.siremono.modules.items.variantattributedefinition.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class EditVariantAttributeDefinitionInput implements CommandInput {
  private UUID id;
  @NotBlank
  private String name;
}
