package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonFindInput implements CommandInput {
  private UUID id;
}
