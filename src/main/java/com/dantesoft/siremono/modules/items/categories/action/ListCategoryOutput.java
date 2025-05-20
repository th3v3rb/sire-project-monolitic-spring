package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * DTO de salida para la operación de listado de categorías. Contiene un payload
 * paginado de entidades de categoría.
 */
@Data
@NoArgsConstructor
@Schema(
    name = "List categories output - success",
    description = "DTO output for listing categories with a paginated payload of category entities.")
public class ListCategoryOutput implements CommandOutput {

  /**
   * Página de entidades de categoría.
   */
  @Schema(
      description = "Página de entidades de categoría",
      implementation = CategoryEntity.class)
  private Page<CategoryEntity> payload;
}
