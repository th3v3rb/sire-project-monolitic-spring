package com.dantesoft.siremono.modules.items.schema.store.dto;

import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;

public record VariantDefinition(
    String value,
    VariantAttributeDefinitionEntity definition
) {

}