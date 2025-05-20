package com.dantesoft.siremono.modules.customer.customer.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.customer.customer.store.CustomerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListCustomersAction extends AbstractCommand<ListCustomersInput, ListCustomersOutput> {
  private final CustomerService customerService;

  @Override
  protected ListCustomersOutput doExecute() {
    var pageable = getInput().getPageable();
    var filter = getInput().getFilter();

    var payload = customerService.findAllByNameOrSocialReason(pageable, filter);

    return AbstractOutput.of(ListCustomersOutput.class, payload);
  }
}
