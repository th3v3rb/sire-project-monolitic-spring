package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.movement.actions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/items/{itemId}/stock")
public class MovementController {
  private final CommandExecutor commandExecutor;

  @GetMapping
  public ResponseEntity<ListMovementsOutput> list(
      @PageableDefault Pageable pageable,
      @PathVariable UUID itemId
  ) {
    var input = new ListMovementsInput();
    input.setPageable(pageable);
    input.setItemID(itemId);

    var out = commandExecutor.execute(ListMovementsAction.class, input);

    return ResponseEntity.ok(out);
  }


  @PostMapping
  public ResponseEntity<AddMovementOutput> add(
      @PathVariable UUID itemId,
      @RequestBody @Valid AddMovementInput input
  ) {
    var out = commandExecutor.execute(AddMovementAction.class, input);
    return ResponseEntity.ok(out);
  }
}
