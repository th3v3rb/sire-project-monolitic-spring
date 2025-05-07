package com.dantesoft.siremono.modules.people.people.actions;

import org.springframework.data.domain.Page;
import com.dantesoft.siremono.internal.commands.CommandOutput;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonListOutput implements CommandOutput {
  private Page<PersonEntity> output;
}
