package com.dantesoft.siremono.modules.items.brands.actions;

import org.springframework.data.domain.Page;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.brands.store.BrandEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Schema(
    name = "List brand output - success",
    description = "Contains the paged list output")
public class ListBrandOutput extends AbstractOutput<Page<BrandEntity>> {
}
