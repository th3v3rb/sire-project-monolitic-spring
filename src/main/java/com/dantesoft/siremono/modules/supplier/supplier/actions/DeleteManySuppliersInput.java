package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DeleteManySuppliersInput implements CommandInput {
  private List<UUID> ids;
}
