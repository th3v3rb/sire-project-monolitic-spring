package com.dantesoft.siremono.modules.items.items.store.dto;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageDTO {
  private byte[] bytes;
  private String contentType;
  private ItemEntity item;
  private boolean main;
}
