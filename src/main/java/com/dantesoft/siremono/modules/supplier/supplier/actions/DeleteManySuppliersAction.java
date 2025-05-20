package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class DeleteManySuppliersAction extends AbstractCommand<DeleteManySuppliersInput, DeleteManySuppliersOutput> {
  private final SupplierService supplierService;

  @Override
  protected DeleteManySuppliersOutput doExecute() {
    var ids = getInput().getIds();

    var drafts = supplierService.findAllWhereIdIn(ids);

    drafts.forEach(e -> e.setDeletedAt(LocalDateTime.now()));

    supplierService.saveAll(drafts);

    return new DeleteManySuppliersOutput();
  }
}
