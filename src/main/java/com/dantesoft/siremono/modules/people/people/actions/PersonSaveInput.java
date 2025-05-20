package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import com.dantesoft.siremono.modules.people.contacts.actions.AttachContactInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonSaveInput implements CommandInput {
  private String socialReason;
  private String document;
  private UUID documentTypeId;
  private AttachContactInput contactInput;
}
