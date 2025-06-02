package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.items.store.dto.ItemImageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemImageService {
  private final ItemImageRepository repository;
  private final AppProperties appProperties;
  private final UploadAdapter uploadAdapter;

  public ItemImageEntity uploadAndSave(ItemImageDTO image) {
    Assert.notNull(image.getItem(), "Item must not be null");
    Assert.notNull(image.getBytes(), "Image bytes must not be null");
    Assert.hasText(image.getContentType(), "Content type must not be empty");

    String objectName = "";
    try {
      String imageName = String.format("%s_item_%s_%d",
          appProperties.getStorage().itemsBucket(),
          image.getItem().getId().toString(),
          System.currentTimeMillis());

      objectName = uploadAdapter.uploadFromBytes(
          appProperties.getStorage().itemsBucket(),
          imageName,
          image.getContentType(),
          image.getBytes()
      );
    } catch (Exception ex) {
      log.error("Error while uploading image", ex);
    }

    var entity = new ItemImageEntity();
    entity.setName(objectName);
    entity.setItem(image.getItem());
    entity.setMain(image.isMain());

    return repository.save(entity);
  }


  public ItemImageEntity save(ItemImageEntity entity) {
    return repository.save(entity);
  }

  public Optional<ItemImageEntity> findMainByItem(ItemEntity item) {
    return repository.findMainByItem(item);
  }

  public void delete(ItemImageEntity entity) {
    repository.delete(entity);
  }

}
