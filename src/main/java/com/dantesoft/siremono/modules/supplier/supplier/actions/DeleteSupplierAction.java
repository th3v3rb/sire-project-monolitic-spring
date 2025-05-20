package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class DeleteSupplierAction extends AbstractCommand<DeleteSupplierInput, DeleteSupplierOutput> {
  private final SupplierService supplierService;

  @Override
  protected DeleteSupplierOutput doExecute() {
    var id = getInput().getId();
    var draft = supplierService.findByIdOrFail(id);
    draft.setDeletedAt(LocalDateTime.now());
    supplierService.save(draft);
    return new DeleteSupplierOutput();
  }
}
