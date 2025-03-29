package com.dantesoft.siremono.modules.items.categories.action;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisableCategoryInput implements ActionInputContract{
  private UUID id;
}
