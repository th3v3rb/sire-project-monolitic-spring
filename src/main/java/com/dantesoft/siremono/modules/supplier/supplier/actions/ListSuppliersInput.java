package com.dantesoft.siremono.modules.supplier.supplier.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class ListSuppliersInput implements CommandInput {
  private Pageable pageable;
  private String filter;
}
