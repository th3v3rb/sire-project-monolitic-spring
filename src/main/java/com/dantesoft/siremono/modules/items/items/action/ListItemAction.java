package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.items.store.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ListItemAction extends AbstractCommand<ListItemInput, ListItemOutput> {
  private final ItemService itemService;
  private final CategoryService categoryService;
  private final UploadAdapter uploadAdapter;
  private final AppProperties app;

  @Override
  public ListItemOutput doExecute() {
    var pageable = getInput().getPageable();
    var searchParam = getInput().getFilter();
    var rawPage = itemService.allBySearchParam(searchParam, pageable);
    var formattedPage = prepareUrl(rawPage);
    
    return AbstractOutput.of(ListItemOutput.class, formattedPage);
  }


  private Page<ItemDTO> prepareUrl(Page<ItemEntity> page) {
    return page.map(element -> {
      Optional<ItemImageEntity> mainImage = element.getImages()
          .stream()
          .filter(ItemImageEntity::isMain)
          .findFirst();

      String imageUrl = mainImage
          .map(this::applySignedUrl)
          .orElse("");


      return new ItemDTO(
          element.getId(),
          element.getName(),
          element.getDescription(),
          element.getBuyPrice(),
          element.getSellPrice(),
          element.getStockQuantity(),
          element.isEnabled(),
          element.getCreatedAt(),
          element.getUpdatedAt(),
          element.getBrand(),
          categoryService.findCategoriesByItemId(element.getId()),
          imageUrl
      );
    });
  }

  private String applySignedUrl(ItemImageEntity image) {
    try {
      return uploadAdapter.getPresignedUrl(
          app.getStorage().itemsBucket(),
          image.getName(),
          3600
      );
    } catch (Exception ex) {
      log.error("Error trying generate presigned url on list", ex);
      return "";
    }
  }
}
