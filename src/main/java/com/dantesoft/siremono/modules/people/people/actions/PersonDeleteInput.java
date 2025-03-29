package com.dantesoft.siremono.modules.people.people.actions;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDeleteInput implements ActionInputContract {
  private UUID id;
}
