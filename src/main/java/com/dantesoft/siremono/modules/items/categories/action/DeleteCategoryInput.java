package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class DeleteCategoryInput implements CommandInput {
  private UUID id;
}
