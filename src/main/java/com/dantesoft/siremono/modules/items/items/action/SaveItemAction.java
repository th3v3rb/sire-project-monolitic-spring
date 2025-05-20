package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageService;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

import static com.dantesoft.siremono.internal.Utils.parseBase64;

@Slf4j
@RequiredArgsConstructor
public class SaveItemAction extends AbstractCommand<SaveItemInput, SaveItemOutput> {
  private final ItemService itemService;
  private final BrandService brandService;
  private final CategoryService categoryService;
  private final ItemImageService itemImageService;
  private final UploadAdapter uploadAdapter;
  private final AppProperties app;

  @Override
  public SaveItemOutput doExecute() {
    var brand = brandService.findByIdOrFail(getInput().getBrandId());
    var item = createItemFromInput(brand);

    var storedItem = itemService.save(item);

    if (getInput().getCategories() != null && !getInput().getCategories().isEmpty()) {
      var categories = ensureCategories();
      storedItem.setCategories(categories);
      storedItem = itemService.save(item);
    }

    if (getInput().getImage() != null) {
      processItemImage(storedItem);
    }

    return AbstractOutput.of(SaveItemOutput.class, storedItem);
  }

  private ItemEntity createItemFromInput(BrandEntity brand) {
    var item = new ItemEntity();
    item.setName(getInput().getName());
    item.setDescription(getInput().getDescription());
    item.setBuyPrice(getInput().getBuyPrice());
    item.setSellPrice(getInput().getSellPrice());
    item.setStockQuantity(0L);
    item.setBrand(brand);
    item.setEnabled(true);
    return item;
  }

  private void processItemImage(ItemEntity item) {
    try {
      var bucket = app.getStorage().itemsBucket();
      var parsed = parseBase64(getInput().getImage());

      var name = uploadAdapter.uploadFromBytes(bucket,
          "item" + item.getId() + System.currentTimeMillis(),
          parsed.contentType(),
          parsed.payload());

      saveItemImageEntity(item, name);

    } catch (Exception e) {
      log.error("Error al guardar la imagen del item {}", item.getId(), e);
      throw new RuntimeException("Error al procesar la imagen del item", e);
    }
  }

  private void saveItemImageEntity(ItemEntity item, String objectName) {
    var itemImage = new ItemImageEntity();
    itemImage.setItem(item);
    itemImage.setName(objectName);
    itemImage.setMain(true);
    itemImage.setEnabled(true);
    itemImageService.save(itemImage);
  }

  private Set<CategoryEntity> ensureCategories() {
    var inputCategories = getInput().getCategories();

    return inputCategories
            .stream()
            .map(categoryService::findByIdOrFail)
            .collect(Collectors.toSet());
  }

}
