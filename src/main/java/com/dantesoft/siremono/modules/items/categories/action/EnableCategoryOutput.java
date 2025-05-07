package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.categories.store.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(
    name = "Enable category output - success",
    description = "DTO output for enabling a category, containing the enabled category entity.")
public class EnableCategoryOutput extends AbstractOutput<CategoryEntity> {

}
