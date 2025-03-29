package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.data.domain.Pageable;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "List brand input",
    description = "Input parameters for the 'List brand' action, including pagination and optional search filters.")
public class ListBrandInput implements ActionInputContract {

  @Schema(description = "Pagination parameters for the brand list.")
  private Pageable pageable;

  @Schema(
      description = "Optional search parameter to filter brands by name. If empty, no name filtering is applied.",
      example = "Nike")
  private String searchParam;

  @Schema(
      description = "Optional filter for brand active status. True returns only active brands, false returns only inactive brands. If null, status filtering is not applied.",
      example = "true")
  private Boolean searchStatusParam;
}