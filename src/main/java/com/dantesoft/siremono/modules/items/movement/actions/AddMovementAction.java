package com.dantesoft.siremono.modules.items.movement.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.items.items.store.ItemService;
import com.dantesoft.siremono.modules.items.movement.store.MovementEntity;
import com.dantesoft.siremono.modules.items.movement.store.MovementService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddMovementAction extends AbstractCommand<AddMovementInput, AddMovementOutput> {
  private final MovementService movementService;
  private final ItemService itemService;


  @Override
  protected AddMovementOutput doExecute() {

    var entity = new MovementEntity();

    var id = getInput().getItemId();
    var item = itemService.findByIdOrFail(id);

    entity.setComment(getInput().getComment());
    entity.setType(getInput().getMovementType());
    entity.setQuantity(getInput().getQuantity());
    entity.setItemName(getInput().getItemName());
    entity.setItem(item);

    movementService.save(entity);

    return new AddMovementOutput();
  }
}
