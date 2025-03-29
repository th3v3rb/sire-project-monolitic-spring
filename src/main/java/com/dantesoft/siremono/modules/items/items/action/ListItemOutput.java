package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.data.domain.Page;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListItemOutput implements ActionOutputContract {
  private Page<ItemEntity> output;
}
