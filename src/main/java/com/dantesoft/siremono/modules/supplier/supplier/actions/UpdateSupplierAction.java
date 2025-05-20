package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateSupplierAction extends AbstractCommand<UpdateSupplierInput, UpdateSupplierOutput> {
  private final SupplierService supplierService;

  @Override
  protected UpdateSupplierOutput doExecute() {
    var id = getInput().getId();
    var draft = supplierService.findByIdOrFail(id);

    draft.setName(getInput().getName());
    draft.setDescription(getInput().getDescription());
    draft.setPersonKind(getInput().getPersonKind());
    draft.setSocialReason(getInput().getSocialReason());

    var payload = supplierService.save(draft);

    return AbstractOutput.of(UpdateSupplierOutput.class, payload);
  }
}
