package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonUpdateOutput implements CommandOutput {
  private PersonEntity person;
}
