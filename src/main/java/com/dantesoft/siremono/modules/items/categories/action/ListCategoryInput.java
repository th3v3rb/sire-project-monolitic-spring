package com.dantesoft.siremono.modules.items.categories.action;

import org.springframework.data.domain.Pageable;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListCategoryInput implements ActionInputContract {
  private Pageable pageable;
  private String searchParam;
}
