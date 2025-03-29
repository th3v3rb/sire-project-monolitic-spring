package com.dantesoft.siremono.modules.people.contacts.actions;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateContactInput implements ActionInputContract {
  private UUID id;
  private String contact;
  private UUID contactTypeId;
}
