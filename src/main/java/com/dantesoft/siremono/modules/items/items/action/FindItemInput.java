package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class FindItemInput implements CommandInput {
  private UUID id;
}
