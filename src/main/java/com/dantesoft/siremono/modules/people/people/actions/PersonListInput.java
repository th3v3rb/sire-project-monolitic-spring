package com.dantesoft.siremono.modules.people.people.actions;

import org.springframework.data.domain.Pageable;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonListInput implements ActionInputContract {
  private Pageable pageable;
  private String searchParam;
}
