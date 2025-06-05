package com.dantesoft.siremono.modules.items.variantattributevalue.store;

import java.math.BigDecimal;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.items.variantattributedefinition.store.VariantAttributeDefinitionEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class VariantAttributeValueEntity extends AbstractEntity {
	@ManyToOne
	private VariantAttributeDefinitionEntity definition;
	private String value;
	private BigDecimal plusPrice;
}
