package com.dantesoft.siremono.modules.items.movement.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.items.movement.store.MovementEntity;
import org.springframework.data.domain.Page;

public class ListMovementsOutput extends AbstractOutput<Page<MovementEntity>> {
}
