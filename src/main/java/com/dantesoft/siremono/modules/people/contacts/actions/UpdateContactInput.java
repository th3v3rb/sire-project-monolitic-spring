package com.dantesoft.siremono.modules.people.contacts.actions;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateContactInput implements CommandInput {
  private UUID id;
  private String contact;
  private UUID contactTypeId;
}
