package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveItemAction extends AbstractAction<SaveItemInput, SaveItemOutput> {
  private final ItemService itemService;
  private final BrandService brandService;
  private final CategoryService categoryService;

  @Override
  public SaveItemOutput doExecute() {
    final var itemName = getInput().getName();
    final var itemDescription = getInput().getDescription();
    final var itemBuyPrice = getInput().getBuyPrice();
    final var itemSellPrice = getInput().getSellPrice();
    final var itemQuantityOnStock = getInput().getQuantityOnStock();
    final var itemIsEnabled = getInput().isEnabled();
    final var brandId = getInput().getBrandId();
    final var categoryId = getInput().getCategoryId();

    final var brand = brandService.findByIdOrFail(brandId);
    final var category = categoryService.findByIdOrFail(categoryId);

    var item = ItemEntity.builder().name(itemName).description(itemDescription)
        .buyPrice(itemBuyPrice).sellPrice(itemSellPrice)
        .quantityOnStock(itemQuantityOnStock).brand(brand).category(category)
        .enabled(itemIsEnabled).build();

    var storedItem = itemService.save(item);

    var out = new SaveItemOutput();
    out.setData(storedItem);
    return out;
  }
}
