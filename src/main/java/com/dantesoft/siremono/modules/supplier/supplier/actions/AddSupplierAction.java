package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierEntity;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddSupplierAction extends AbstractCommand<AddSupplierInput, AddSupplierOutput> {
  private final SupplierService supplierService;

  @Override
  protected AddSupplierOutput doExecute() {
    var draft = SupplierEntity
        .builder()
        .name(getInput().getName())
        .description(getInput().getDescription())
        .personKind(getInput().getPersonKind())
        .socialReason(getInput().getSocialReason())
        .build();

    var payload = supplierService.save(draft);

    return AbstractOutput.of(AddSupplierOutput.class, payload);
  }
}
