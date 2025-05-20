package com.dantesoft.siremono.modules.customer.customer.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.customer.customer.store.CustomerEntity;
import org.springframework.data.domain.Page;

public class ListCustomersOutput extends AbstractOutput<Page<CustomerEntity>> {

}
