package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Schema(
    name = "List item input",
    description = "The necesary arguments to execute 'List items' action")
public class ListItemInput implements CommandInput {
  @Schema(description = "Pagination options")
  private Pageable pageable;
  @Schema(description = "Search by name option")
  private String searchParam;
}
