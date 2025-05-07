package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name="Save category output - success", description = "The success output of the save category action")
public class SaveCategoryOutput extends AbstractOutput<CategoryEntity>{
}
