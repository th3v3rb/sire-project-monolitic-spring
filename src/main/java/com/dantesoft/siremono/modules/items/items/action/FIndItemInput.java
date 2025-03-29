package com.dantesoft.siremono.modules.items.items.action;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FIndItemInput implements ActionInputContract {
  private UUID id;
}
