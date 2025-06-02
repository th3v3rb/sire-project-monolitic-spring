package com.dantesoft.siremono.internal.database.seeders;

import com.dantesoft.siremono.internal.Utils;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandService;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemImageService;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.items.store.dto.ItemImageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductsSeeder implements DatabaseSeeder {
  private final BrandService brandService;
  private final CategoryService categoryService;
  private final ItemService itemService;
  private final ItemImageService itemImageService;
  private final Faker faker;

  private static final int CATEGORY_COUNT = 50;
  private static final int BRAND_COUNT = 50;
  private static final int ITEM_COUNT = 100;

  @Override
  public void seed() {
    try {
      log.info("Starting product seed...");

      CompletableFuture<List<CategoryEntity>> categoriesFuture = CompletableFuture.supplyAsync(this::seedCategoriesIfNeeded);
      CompletableFuture<List<BrandEntity>> brandsFuture = CompletableFuture.supplyAsync(this::seedBrandsIfNeeded);

      CompletableFuture<Void> itemsFuture = categoriesFuture.thenCombineAsync(brandsFuture, (categories, brands) -> {
        seedItemsIfNeeded(categories, brands);
        return null;
      });

      CompletableFuture.allOf(categoriesFuture, brandsFuture, itemsFuture).join();

      log.info("Product seed completed.");
    } catch (Exception ex) {
      log.error("Error seeding products", ex);
    }
  }


  private List<CategoryEntity> seedCategoriesIfNeeded() {
    if (!isCategoriesEmpty()) {
      log.info("Categories already exist. Skipping.");
      return categoryService.findAll();
    }

    log.info("Seeding {} categories...", CATEGORY_COUNT);

    return IntStream.range(0, CATEGORY_COUNT)
        .parallel()
        .mapToObj(i -> {
          String name = faker.commerce().department();
          CategoryEntity category = new CategoryEntity(name);
          return categoryService.save(category);
        })
        .toList();
  }

  private List<BrandEntity> seedBrandsIfNeeded() {
    if (!isBrandsEmpty()) {
      log.info("Brands already exist. Skipping.");
      return brandService.findAll();
    }

    log.info("Seeding {} brands...", BRAND_COUNT);

    return IntStream.range(0, BRAND_COUNT)
        .parallel()
        .mapToObj(i -> {
          String name = faker.brand().sport();
          BrandEntity brand = new BrandEntity(name);
          return brandService.save(brand);
        })
        .toList();
  }

  private void seedItemsIfNeeded(List<CategoryEntity> categories, List<BrandEntity> brands) {
    if (!isItemsEmpty()) {
      log.info("Items already exist. Skipping.");
      return;
    }

    log.info("Seeding {} items...", ITEM_COUNT);

    IntStream.range(0, ITEM_COUNT)
        .parallel()
        .forEach(i -> {
          String name = faker.commerce().productName();
          String description = faker.lorem().sentence();
          BigDecimal buyPrice = BigDecimal.valueOf(faker.number().randomDouble(2, 10, 100));
          BigDecimal sellPrice = buyPrice.add(BigDecimal.valueOf(faker.number().randomDouble(2, 5, 30)));
          int stock = faker.number().numberBetween(10, 200);

          BrandEntity randomBrand = getRandomElement(brands);
          HashSet<CategoryEntity> randomCategories = getRandomSubset(categories, 1 + ThreadLocalRandom.current().nextInt(3));

          ItemEntity item = ItemEntity.builder()
              .name(name)
              .description(description)
              .buyPrice(buyPrice)
              .sellPrice(sellPrice)
              .stockQuantity((long) stock)
              .brand(randomBrand)
              .categories(randomCategories)
              .build();

          var entity = itemService.save(item);

          String image = faker.image().base64JPG();
          var parsedImage = Utils.parseBase64(image);
          itemImageService.uploadAndSave(
              new ItemImageDTO(
                  parsedImage.payload(),
                  parsedImage.contentType(),
                  entity,
                  true
              )
          );
        });
  }

  private <T> T getRandomElement(List<T> list) {
    int index = ThreadLocalRandom.current().nextInt(list.size());
    return list.get(index);
  }

  private <T> HashSet<T> getRandomSubset(List<T> list, int count) {
    HashSet<T> result = new HashSet<>();
    while (result.size() < count) {
      result.add(getRandomElement(list));
    }
    return result;
  }

  private boolean isCategoriesEmpty() {
    return categoryService.findAll().isEmpty();
  }

  private boolean isBrandsEmpty() {
    return brandService.findAll().isEmpty();
  }

  private boolean isItemsEmpty() {
    return itemService.findAll().isEmpty();
  }
}
