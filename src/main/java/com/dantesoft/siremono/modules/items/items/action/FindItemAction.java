package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FindItemAction extends AbstractCommand<FIndItemInput, FindItemOutput> {
	private final ItemService itemService;

	@Override
	public FindItemOutput doExecute() {
		var id = getInput().getId();

		var storedItem = itemService.findByIdOrFail(id);

		return AbstractOutput.of(FindItemOutput.class, storedItem);
	}

}
