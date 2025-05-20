package com.dantesoft.siremono.modules.items.items.store;

import com.dantesoft.siremono.internal.database.AbstractJsonEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
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
          fetch = FetchType.EAGER,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
          }
  )
  @JoinTable(
          name = "item_has_categories",
          joinColumns = @JoinColumn(name = "item_id"),
          inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<CategoryEntity> categories = Collections.emptySet();

  @OneToMany(
          fetch = FetchType.EAGER,
          cascade = CascadeType.ALL, mappedBy = "item",
          orphanRemoval = true
  )
  private Set<ItemImageEntity> images = Collections.emptySet();
}
