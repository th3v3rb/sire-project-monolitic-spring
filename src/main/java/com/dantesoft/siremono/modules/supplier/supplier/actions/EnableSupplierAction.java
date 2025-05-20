package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnableSupplierAction extends AbstractCommand<EnableSupplierInput, EnableSupplierOutput> {
  private final SupplierService supplierService;

  @Override
  protected EnableSupplierOutput doExecute() {
    var id = getInput().getId();
    var draft = supplierService.findByIdOrFail(id);
    draft.setEnabled(true);
    var payload = supplierService.save(draft);
    return AbstractOutput.of(EnableSupplierOutput.class, payload);
  }
}
