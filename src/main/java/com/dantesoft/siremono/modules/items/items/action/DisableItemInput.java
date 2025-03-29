package com.dantesoft.siremono.modules.items.items.action;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;

@Data
public class DisableItemInput implements ActionInputContract {
  private UUID id;
}
