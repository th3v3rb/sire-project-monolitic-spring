package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.supplier.supplier.store.SupplierEntity;
import org.springframework.data.domain.Page;

public class ListSuppliersOutput extends AbstractOutput<Page<SupplierEntity>> {
}
