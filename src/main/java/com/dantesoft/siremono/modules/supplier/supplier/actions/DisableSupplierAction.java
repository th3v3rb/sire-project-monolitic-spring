package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DisableSupplierAction extends AbstractCommand<DisableSupplierInput, DisableSupplierOutput> {
  private final SupplierService supplierService;

  @Override
  protected DisableSupplierOutput doExecute() {
    var id = getInput().getId();
    var draft = supplierService.findByIdOrFail(id);
    draft.setEnabled(false);
    var payload = supplierService.save(draft);
    return AbstractOutput.of(DisableSupplierOutput.class, payload);
  }

}
