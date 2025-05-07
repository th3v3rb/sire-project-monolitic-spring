package com.dantesoft.siremono.modules.people.people.actions;

import org.springframework.data.domain.Pageable;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonListInput implements CommandInput {
  private Pageable pageable;
  private String searchParam;
}
