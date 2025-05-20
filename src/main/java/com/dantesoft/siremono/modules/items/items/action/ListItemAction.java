package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.connectors.upload.UploadAdapter;
import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.internal.config.AppProperties;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.items.store.views.ItemDTO;
import com.dantesoft.siremono.modules.items.items.store.views.ItemView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ListItemAction extends AbstractCommand<ListItemInput, ListItemOutput> {
  private final ItemService itemService;
  private final UploadAdapter uploadAdapter;
  private final AppProperties app;

  @Override
  public ListItemOutput doExecute() {
    var pageable = getInput().getPageable();
    var searchParam = getInput().getSearchParam();
    Page<ItemView> page = itemService.allBySearchParam(searchParam, pageable);

    var signedItem = page.map(rawItem -> {

      var parsedImages = rawItem.getImages().stream().map(element -> {
        try {
          var signed = uploadAdapter
              .getPresignedUrl(app.getStorage().itemsBucket(), element.getName(), 60 * 5);
          return signed;
        } catch (Exception e1) {
          e1.printStackTrace();
          return null;
        }
      }).collect(Collectors.toSet());

      var dto = new ItemDTO(
          rawItem.getId(), rawItem.getName(), rawItem.getDescription(), rawItem.getBuyPrice(),
          rawItem.getSellPrice(), rawItem.getStockQuantity(), rawItem.isEnabled(),
          rawItem.getCreatedAt(), rawItem.getUpdatedAt(), rawItem.getBrand(),
          rawItem.getCategories(), parsedImages);

      return dto;
    });

    return AbstractOutput.of(ListItemOutput.class, signedItem);
  }


}
