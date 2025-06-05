package com.dantesoft.siremono.modules.items.schema.store.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VariantDTO {
  private String name;
  private BigDecimal price;
  private Integer quantity;
  private boolean enabled;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List<VariantDefinition> definitions;
}
