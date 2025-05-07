package com.dantesoft.siremono.modules.people.contacts.actions;

import java.util.UUID;
import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachContactInput implements CommandInput {
  private String description;
  private UUID contactTypeId;
  private UUID personId;
}
