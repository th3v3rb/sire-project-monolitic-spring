package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonUpdateInput implements CommandInput {
  private UUID id;
  private String socialReason;
  private String document;
  private UUID documentTypeId;
}
