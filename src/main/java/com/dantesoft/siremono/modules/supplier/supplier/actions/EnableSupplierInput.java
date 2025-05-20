package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;

import java.util.UUID;

@Data
public class EnableSupplierInput implements CommandInput {
  private UUID id;
}
