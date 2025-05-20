package com.dantesoft.siremono.modules.customer.customer.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class ListCustomersInput implements CommandInput {
  private Pageable pageable;
  private String filter;
}
