package com.dantesoft.siremono.modules.items.items.store;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.dantesoft.siremono.internal.database.AbstractJsonEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "items")
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
  @Builder.Default
  private Set<CategoryEntity> categories = new HashSet<>();

  @OneToMany(
      mappedBy = "item",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @Builder.Default
  private Set<ItemImageEntity> images = new HashSet<>();

}
