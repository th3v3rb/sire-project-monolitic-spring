package com.dantesoft.siremono.modules.items.movement.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.modules.items.movement.store.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddMovementInput implements CommandInput {

  @NotNull
  private UUID itemId;
  @NotBlank
  private String itemName;
  @NotNull
  private Integer quantity;
  @NotNull
  private MovementType movementType;
  private String comment;

}
