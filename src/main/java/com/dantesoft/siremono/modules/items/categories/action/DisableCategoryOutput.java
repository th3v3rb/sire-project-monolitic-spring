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
    name = "Disable category output - success",
    description = "DTO output for disabling a category, containing the disabled category entity.")
public class DisableCategoryOutput extends AbstractOutput<CategoryEntity> {

}
