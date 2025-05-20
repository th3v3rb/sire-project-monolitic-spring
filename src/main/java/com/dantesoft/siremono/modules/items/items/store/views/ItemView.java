package com.dantesoft.siremono.modules.items.items.store.views;

import com.dantesoft.siremono.modules.items.brands.store.BrandView;
import com.dantesoft.siremono.modules.items.categories.store.CategoryView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


public interface ItemView {
  UUID getId();

  String getName();

  String getDescription();

  BigDecimal getBuyPrice();

  BigDecimal getSellPrice();

  Long getStockQuantity();

  Boolean isEnabled();

  LocalDateTime getCreatedAt();

  LocalDateTime getUpdatedAt();

  BrandView getBrand();

  Set<CategoryView> getCategories();

  Set<ItemImageView> getImages();

}
