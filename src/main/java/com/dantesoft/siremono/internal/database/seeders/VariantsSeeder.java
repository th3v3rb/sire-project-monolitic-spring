package com.dantesoft.siremono.internal.database.seeders;

import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.variant.store.VariantEntity;
import com.dantesoft.siremono.modules.items.variant.store.VariantService;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionService;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueEntity;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class VariantsSeeder implements DatabaseSeeder {

  private final VariantAttributeDefinitionService definitionService;
  private final VariantAttributeValueService valueService;
  private final VariantService variantService;
  private final ItemService itemService;

  private final Faker faker = new Faker();

  private static final int DEFINITION_COUNT = 100;

  @Override
  public void seed() {
    log.info("Seeding variant definitions and variants...");

    List<ItemEntity> items = itemService.findAll();
    if (items.isEmpty()) {
      log.warn("No items found. Variants won't be seeded.");
      return;
    }

    if (definitionService.count() > 0 || valueService.count() > 0 || variantService.count() > 0) {
      log.info("Already exist records. Skipping.");
      return;
    }

    List<VariantAttributeDefinitionEntity> definitions = IntStream.range(0, DEFINITION_COUNT)
        .parallel()
        .mapToObj(i -> {
          String name = faker.commerce().material() + "-" + i;
          return VariantAttributeDefinitionEntity.builder()
              .name(name)
              .build();
        })
        .map(definitionService::save)
        .toList();

    definitions.parallelStream().forEach(def -> {
      VariantEntity variant = createVariant(randomItem(items));
      VariantAttributeValueEntity attrValue = new VariantAttributeValueEntity();
      attrValue.setVariant(variant);
      attrValue.setDefinition(def);
      attrValue.setValue(null);

      variant.setAttributeValues(List.of(attrValue));
      variantService.save(variant);

      valueService.save(attrValue);
    });

    log.info("Variant seeding completed.");
  }

  private VariantEntity createVariant(ItemEntity item) {
    return VariantEntity.builder()
        .item(item)
        .name(faker.commerce().productName())
        .sku(UUID.randomUUID().toString())
        .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10, 200)))
        .quantity(ThreadLocalRandom.current().nextInt(1, 100))
        .attributeValues(new ArrayList<>())
        .build();
  }

  private ItemEntity randomItem(List<ItemEntity> items) {
    return items.get(ThreadLocalRandom.current().nextInt(items.size()));
  }


}
