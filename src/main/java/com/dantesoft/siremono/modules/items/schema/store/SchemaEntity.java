package com.dantesoft.siremono.modules.items.schema.store;

import java.util.List;

import com.dantesoft.siremono.internal.database.AbstractEntity;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.variantattributevalue.store.VariantAttributeValueEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SchemaEntity extends AbstractEntity {
	@ManyToOne
	private ItemEntity item;
	@OneToMany
	private List<VariantAttributeValueEntity> value;

}
