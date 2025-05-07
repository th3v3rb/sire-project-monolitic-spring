package com.dantesoft.siremono.modules.items.items.store.views;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import com.dantesoft.siremono.modules.items.brands.store.BrandView;
import com.dantesoft.siremono.modules.items.categories.store.CategoryView;

public record ItemDTO(UUID id, String name, String description, BigDecimal buyPrice,
    BigDecimal sellPrice, Long stockQuantity, boolean enabled, LocalDateTime createdAt,
    LocalDateTime updatedAt, BrandView brand, Set<CategoryView> categories, Set<String> images) {
}
