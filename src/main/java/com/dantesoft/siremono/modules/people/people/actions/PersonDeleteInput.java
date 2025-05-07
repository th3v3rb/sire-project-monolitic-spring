package com.dantesoft.siremono.modules.people.people.actions;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDeleteInput implements CommandInput {
  private UUID id;
}
