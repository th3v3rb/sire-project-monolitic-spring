package com.dantesoft.siremono.modules.items.brands.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Schema(name = "List brand input", description = "Input parameters for the 'List brand' action, including pagination and optional search filters.")
public class ListBrandInput implements CommandInput {

	@Schema(description = "Pagination parameters for the brand list.")
	private Pageable pageable;

	@Schema(description = "Optional search parameter to filter brands by name. If empty, no name filtering is applied.", example = "Nike")
	private String searchParam;
}