package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;

import java.util.UUID;

@Data
public class DisableItemInput implements CommandInput {
  private UUID id;
}
