package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
public class ListCategoryInput implements CommandInput {
  private Pageable pageable;
  private String searchParam;
}
