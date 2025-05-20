package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
public class PersonListOutput implements CommandOutput {
  private Page<PersonEntity> output;
}
