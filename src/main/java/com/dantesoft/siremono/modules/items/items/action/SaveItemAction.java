package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
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

import java.util.HashSet;
import java.util.Optional;

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
    var input = getInput();

    var brand = brandService.findByIdOrFail(input.getBrandId());
    var categories = categoryService.findAllById(input.getCategories());
    var item = buildNewItem(input, brand);

    item.setCategories(new HashSet<>(categories));
    var storedItem = itemService.save(item);

    Optional.ofNullable(input.getImage())
        .ifPresent(img -> uploadAndAttachMainImage(storedItem, img));

    var imageUrl = getPresignedMainImageUrl(storedItem);

    var payload = new ItemDTO(
        storedItem.getId(),
        storedItem.getName(),
        storedItem.getDescription(),
        storedItem.getBuyPrice(),
        storedItem.getSellPrice(),
        storedItem.getStockQuantity(),
        storedItem.isEnabled(),
        storedItem.getCreatedAt(),
        storedItem.getUpdatedAt(),
        storedItem.getBrand(),
        storedItem.getCategories(),
        imageUrl
    );

    return AbstractOutput.of(SaveItemOutput.class, payload);
  }

  private ItemEntity buildNewItem(SaveItemInput input, BrandEntity brand) {
    var item = new ItemEntity();
    item.setName(input.getName());
    item.setDescription(input.getDescription());
    item.setBuyPrice(input.getBuyPrice());
    item.setSellPrice(input.getSellPrice());
    item.setStockQuantity(0L);
    item.setBrand(brand);
    item.setEnabled(true);
    return item;
  }

  private void uploadAndAttachMainImage(ItemEntity item, String base64Image) {
    var parsed = parseBase64(base64Image);
    itemImageService.uploadAndSave(
        new ItemImageDTO(
            parsed.payload(),
            parsed.contentType(),
            item,
            true
        )
    );
  }

  private String getPresignedMainImageUrl(ItemEntity item) {
    return item.getImages().stream()
        .filter(ItemImageEntity::isMain)
        .findFirst()
        .map(img -> {
          try {
            return uploadAdapter.getPresignedUrl(
                app.getStorage().itemsBucket(),
                img.getName(),
                3600
            );
          } catch (Exception e) {
            log.error("Error al generar URL presignada para la imagen del item {}", item.getId(), e);
            return "";
          }
        })
        .orElse("");
  }
}
