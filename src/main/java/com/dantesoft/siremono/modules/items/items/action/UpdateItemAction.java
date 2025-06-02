package com.dantesoft.siremono.modules.items.items.action;


import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.Utils;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageService;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.items.store.dto.ItemDTO;
import com.dantesoft.siremono.modules.items.items.store.dto.ItemImageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashSet;

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

    return tx.execute(ignore -> {
      var input = getInput();
      var item = itemService.findByIdOrFail(input.getId());
      var brand = brandService.findByIdOrFail(input.getBrandId());
      var categories = categoryService.findAllById(getInput().getCategories());

      item.setName(input.getName());
      item.setDescription(input.getDescription());
      item.setBuyPrice(input.getBuyPrice());
      item.setSellPrice(input.getSellPrice());
      item.setStockQuantity(0L);
      item.setBrand(brand);
      item.setCategories(new HashSet<>(categories));


      var updatedItem = itemService.save(item);

      ItemImageEntity image = updateMainImage(updatedItem);

      var payload = new ItemDTO(
          updatedItem.getId(),
          updatedItem.getName(),
          updatedItem.getDescription(),
          updatedItem.getBuyPrice(),
          updatedItem.getSellPrice(),
          updatedItem.getStockQuantity(),
          updatedItem.isEnabled(),
          updatedItem.getCreatedAt(),
          updatedItem.getUpdatedAt(),
          updatedItem.getBrand(),
          updatedItem.getCategories(),
          getPresignedMainImageUrl(updatedItem, image)
      );

      return AbstractOutput.of(UpdateItemOutput.class, payload);
    });
  }

  private ItemImageEntity updateMainImage(ItemEntity item) {
    if (getInput().getImage() == null || getInput().getImage().isEmpty()) return null;

    var parsedImage = Utils.parseBase64(getInput().getImage());

    itemImageService.findMainByItem(item).ifPresent(image -> {
      image.setEnabled(false);
      itemImageService.save(image);
      log.info("Old image has been disabled: {}", image.getName());
    });

    return itemImageService.uploadAndSave(
        new ItemImageDTO(
            parsedImage.payload(),
            parsedImage.contentType(),
            item,
            true
        )
    );
  }

  private String getPresignedMainImageUrl(ItemEntity item, ItemImageEntity image) {
    if (image == null) {
      image = item.getImages().stream().filter(ItemImageEntity::isMain).findFirst().orElse(null);
    }

    if (image == null) return "";

    try {
      return uploadAdapter.getPresignedUrl(
          app.getStorage().itemsBucket(),
          image.getName(),
          3600
      );
    } catch (Exception e) {
      log.error("Error al generar URL presignada para item {}", image.getId(), e);
      return "";
    }
  }

}
