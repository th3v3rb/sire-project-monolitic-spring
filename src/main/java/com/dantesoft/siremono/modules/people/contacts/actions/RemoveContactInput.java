package com.dantesoft.siremono.modules.people.contacts.actions;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RemoveContactInput implements CommandInput {
  private UUID contactId;
}
