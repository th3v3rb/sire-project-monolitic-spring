package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.data.domain.Pageable;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListCategoryInput implements CommandInput {
  private Pageable pageable;
  private String searchParam;
}
