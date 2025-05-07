package com.dantesoft.siremono.modules.items.items.action;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FIndItemInput implements CommandInput {
  private UUID id;
}
