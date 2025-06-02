package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.internal.validation.ValidEnum;
import com.dantesoft.siremono.modules.actor.actor.store.PersonKind;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateSupplierInput implements CommandInput {
  private UUID id;
  @NotBlank
  private String name;
  @NotBlank
  private String socialReason;
  private String description;
  @ValidEnum(enumClass = PersonKind.class)
  private PersonKind personKind;
}
