package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.internal.database.AbstractJsonEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import com.dantesoft.siremono.modules.items.variant.store.VariantEntity;
import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends AbstractJsonEntity<ItemEntityData> {
  private String name;
  private String description;
  private BigDecimal buyPrice;
  private BigDecimal sellPrice;
  private Long stockQuantity;

  @ManyToOne(fetch = FetchType.EAGER)
  private BrandEntity brand;

  @ManyToMany(
      fetch = FetchType.EAGER
  )
  @JoinTable(
      name = "item_has_categories",
      joinColumns = @JoinColumn(name = "item_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<CategoryEntity> categories = Collections.emptySet();

  @OneToMany(
      mappedBy = "item",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<ItemImageEntity> images = Collections.emptySet();

  @OneToMany(
      mappedBy = "item",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<VariantEntity> variants = new ArrayList<>();

}
