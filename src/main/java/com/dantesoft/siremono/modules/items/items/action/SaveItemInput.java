package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Schema(name = "Save item input", description = "The data necessary for register a item on the system")
public class SaveItemInput implements CommandInput {

	@Nullable
	@Schema(description = "The base64 encoded image of the item")
	private String image;

	@Size(min = 3)
	@NotBlank
	@Schema(description = "The name of the item")
	private String name;

	@Schema(description = "The description of the item, procedency, another names, etc. Its Optional.")
	private String description;

	@NotNull
	@PositiveOrZero
	@Schema(description = "The buy price of the item, put 0 if not has a buy price.")
	private BigDecimal buyPrice;

	@NotNull
	@PositiveOrZero
	@Schema(description = "The sell price of the item, put 0 if not has a sell price.")
	private BigDecimal sellPrice;

	@NotNull
	@Schema(description = "The related brand identifier, it's mandatory. Put the id of NO BRAND ID if not has a brand")
	private UUID brandId;

	@NotNull
	@Schema(description = "The related category identifier, it's mandatory. Put NO CATEGORY ID if not has a category")
	private List<UUID> categories;
}
