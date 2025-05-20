package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListSuppliersAction extends AbstractCommand<ListSuppliersInput, ListSuppliersOutput> {
  private final SupplierService supplierService;

  @Override
  protected ListSuppliersOutput doExecute() {
    var pageable = getInput().getPageable();
    var filter = getInput().getFilter();

    var payload = supplierService.list(pageable, filter);

    return AbstractOutput.of(ListSuppliersOutput.class, payload);
  }
}
