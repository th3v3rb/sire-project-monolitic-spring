package com.dantesoft.siremono.modules.items.items.action;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnableItemAction extends AbstractCommand<EnableItemInput, EnableItemOutput> {

	private final ItemService itemService;

	@Override
	public EnableItemOutput doExecute() {
		var id = getInput().getId();

		var item = itemService.findByIdOrFail(id);
		item.setEnabled(true);
		itemService.save(item);

		return new EnableItemOutput();
	}

}
