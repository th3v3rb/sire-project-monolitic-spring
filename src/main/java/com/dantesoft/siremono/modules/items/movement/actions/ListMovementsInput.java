package com.dantesoft.siremono.modules.items.movement.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Data
public class ListMovementsInput implements CommandInput {
  private Pageable pageable;
  private UUID itemID;
}
