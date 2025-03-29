package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindItemOutput implements ActionOutputContract {
  private ItemEntity data;
}
