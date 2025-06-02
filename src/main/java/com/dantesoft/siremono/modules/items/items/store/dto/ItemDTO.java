package com.dantesoft.siremono.modules.items.items.store.dto;

import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ItemDTO(
    UUID id,
    String name,
    String description,
    BigDecimal buyPrice,
    BigDecimal sellPrice,
    Long stockQuantity,
    boolean enabled,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    BrandEntity brand,
    Set<CategoryEntity> categories,
    String image
) {
}
