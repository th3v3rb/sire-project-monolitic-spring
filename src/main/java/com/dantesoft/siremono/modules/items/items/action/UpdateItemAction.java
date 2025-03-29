package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateItemAction extends AbstractAction<UpdateItemInput, UpdateItemOutput> {
  private final ItemService itemService;
  private final CategoryService categoryService;
  private final BrandService brandService;

  @Override
  public UpdateItemOutput doExecute() {
    var itemId = getInput().getId();
    var brandId = getInput().getBrandId();
    var categoryId = getInput().getCategoryId();

    var itemFound = itemService.findByIdOrFail(itemId);
    var brandFound = brandService.findByIdOrFail(brandId);
    var categoryFound = categoryService.findByIdOrFail(categoryId);

    var itemToUpdate = itemFound.toBuilder().name(getInput().getName())
        .description(getInput().getDescription())
        .buyPrice(getInput().getBuyPrice()).sellPrice(getInput().getSellPrice())
        .quantityOnStock(getInput().getQuantityOnStock()).brand(brandFound)
        .category(categoryFound).build();

    var updatedItem = itemService.save(itemToUpdate);

    var out = new UpdateItemOutput();
    out.setData(updatedItem);
    return out;
  }

}
