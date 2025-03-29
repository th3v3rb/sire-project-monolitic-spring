package com.dantesoft.siremono.modules.items.items.store;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.dantesoft.siremono.internal.AbstractEntity;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "items")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends AbstractEntity {
  private String name;
  private String description;
  private BigDecimal buyPrice;
  private BigDecimal sellPrice;
  private Long quantityOnStock;
  private String imageUrl;
  private boolean enabled;

  @ManyToOne
  private BrandEntity brand;

  @ManyToOne
  private CategoryEntity category;

  @JdbcTypeCode(SqlTypes.JSON)
  private String questions;

  @Transient
  @JsonIgnore
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Transient
  public List<Map<String, List<String>>> getQuestionsList() {
    try {
      return objectMapper.readValue(
          this.questions, new TypeReference<List<Map<String, List<String>>>>() {
          });
    } catch (IOException e) {
      throw new RuntimeException("Error parsing JSON", e);
    }
  }

  @Transient
  public void setQuestionsList(List<Map<String, List<String>>> questionsList) {
    try {
      this.questions = objectMapper.writeValueAsString(questionsList);
    } catch (IOException e) {
      throw new RuntimeException("Error writing JSON", e);
    }
  }
}
