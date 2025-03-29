package com.dantesoft.siremono.modules.items.items.action;

import org.springframework.data.domain.Pageable;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "List item input",
    description = "The necesary arguments to execute 'List items' action")
public class ListItemInput implements ActionInputContract {
  @Schema(description = "Pagination options")
  private Pageable pageable;
  @Schema(description = "Search by name option")
  private String searchParam;
}
