package com.dantesoft.siremono.modules.items.categories.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.categories.store.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DisableCategoryAction extends AbstractCommand<DisableCategoryInput, DisableCategoryOutput> {
	private final CategoryService service;

	@Override
	public DisableCategoryOutput doExecute() {
		var id = getInput().getId();
		var out = new DisableCategoryOutput();

		var category = service.findByIdOrFail(id);
		category.setEnabled(false);
		var disabledCategory = service.save(category);

		out.setPayload(disabledCategory);
		return out;
	}

}
