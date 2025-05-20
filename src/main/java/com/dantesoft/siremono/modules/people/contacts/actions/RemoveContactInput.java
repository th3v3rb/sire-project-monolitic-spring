package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RemoveContactInput implements CommandInput {
  private UUID contactId;
}
