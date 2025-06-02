package com.dantesoft.siremono.modules.items.movement.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.items.store.ItemEntity;
import com.dantesoft.siremono.modules.items.movement.store.MovementService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListMovementsAction extends AbstractCommand<ListMovementsInput, ListMovementsOutput> {
  private final MovementService movementService;

  @Override
  protected ListMovementsOutput doExecute() {
    var item = new ItemEntity();
    item.setId(getInput().getItemID());

    var payload = movementService.listAll(
        getInput().getPageable(),
        item
    );

    return AbstractOutput.of(ListMovementsOutput.class, payload);
  }
}
