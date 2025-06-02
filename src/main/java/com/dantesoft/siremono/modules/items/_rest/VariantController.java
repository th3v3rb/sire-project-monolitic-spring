package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.variant.actions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/{itemId}/variants")
@RequiredArgsConstructor
public class VariantController {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<ListVariantsOutput> list(
      @PathVariable UUID itemId,
      @PageableDefault Pageable pageable,
      @RequestParam(required = false, defaultValue = "") String filter
  ) {
    var input = new ListVariantsInput(filter, itemId, pageable);
    var out = handler.execute(ListVariantsAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping
  public ResponseEntity<AddVariantOutput> add(
      @PathVariable UUID itemId,
      @RequestBody @Valid AddVariantInput input
  ) {
    input.setItemId(itemId);

    var out = handler.execute(AddVariantAction.class, input);

    return ResponseEntity.ok(out);
  }
}
