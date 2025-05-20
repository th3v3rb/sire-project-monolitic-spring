package com.dantesoft.siremono.modules.customer.customer.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.modules.actor.actor.store.PersonKind;
import lombok.Data;

@Data
public class AddCustomerInput implements CommandInput {
  private String name;
  private String socialReason;
  private String description;
  private PersonKind personKind;
}
