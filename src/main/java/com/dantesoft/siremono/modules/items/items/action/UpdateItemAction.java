package com.dantesoft.siremono.modules.items.items.action;


import static com.dantesoft.siremono.internal.Utils.parseBase64;
import java.util.stream.Collectors;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageService;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UpdateItemAction extends AbstractCommand<UpdateItemInput, UpdateItemOutput> {
  private final ItemService itemService;
  private final BrandService brandService;
  private final CategoryService categoryService;
  private final ItemImageService itemImageService;
  private final UploadAdapter uploadAdapter;
  private final PlatformTransactionManager txManager;
  private final AppProperties app;


  @Override
  public UpdateItemOutput doExecute() {
    TransactionTemplate tx = new TransactionTemplate(txManager);

    return tx.execute(_ -> {
      var input = getInput();
      var item = itemService.findByIdOrFail(input.getId());
      var brand = brandService.findByIdOrFail(input.getBrandId());

      item.setName(input.getName());
      item.setDescription(input.getDescription());
      item.setBuyPrice(input.getBuyPrice());
      item.setSellPrice(input.getSellPrice());
      item.setStockQuantity(input.getStockQuantity());
      item.setBrand(brand);



      var categories = input.getCategories().stream().map(e -> {
        return this.categoryService.findByIdOrFail(e);
      }).collect(Collectors.toSet());

      item.setCategories(categories);

      if (input.getImage() != null && !input.getImage().isEmpty()) {
        disableOldImage(item);
        processItemImage(item);
      }

      var updatedItem = itemService.save(item);
      return AbstractOutput.of(UpdateItemOutput.class, updatedItem);

    });
  }

  private void disableOldImage(ItemEntity item) {
    itemImageService.findMainByItem(item).ifPresent(image -> {
      image.setEnabled(false);
      itemImageService.save(image);
      log.info("Imagen anterior eliminada: {}", image.getName());
    });
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

}
