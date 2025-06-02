package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.variantattributedefinition.actions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/variants/attributes")
@RequiredArgsConstructor
public class VariantAttributeDefinitionController {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<ListVariantsAttributesDefinitionOutput> list(
      @PageableDefault Pageable pageable,
      @RequestParam(required = false, defaultValue = "") String filter
  ) {
    var input = new ListVariantsAttributesDefinitionInput(pageable, filter);
    var out = handler.execute(ListVariantsAttributesDefinitionAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping
  public ResponseEntity<AddVariantAttributeDefinitionOutput> add(
      @RequestBody @Valid AddVariantAttributeDefinitionInput input
  ) {
    var out = handler.execute(AddVariantAttributeDefinitionAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping("/delete")
  public ResponseEntity<DeleteManyVariantsAttributeDefinitionOutput> deleteAll(
      @RequestBody @Valid DeleteManyVariantsAttributeDefinitionInput input
  ) {
    handler.execute(DeleteManyVariantsAttributeDefinitionAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<EditVariantAttributeDefinitionOutput> edit(
      @RequestBody @Valid EditVariantAttributeDefinitionInput input,
      @PathVariable UUID id
  ) {
    input.setId(id);
    var out = handler.execute(EditVariantAttributeDefinitionAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PatchMapping("/{id}/enable")
  public ResponseEntity<EnableVariantAttributeDefinitionOutput> enable(
      @PathVariable UUID id
  ) {
    var arg = new EnableVariantAttributeDefinitionInput(id);
    var out = handler.execute(EnableVariantAttributeDefinitionAction.class, arg);
    return ResponseEntity.ok(out);
  }

  @PatchMapping("/{id}/disable")
  public ResponseEntity<DisableVariantAttributeDefinitionOutput> disable(
      @PathVariable UUID id
  ) {
    var arg = new DisableVariantAttributeDefinitionInput(id);
    var out = handler.execute(DisableVariantAttributeDefinitionAction.class, arg);
    return ResponseEntity.ok(out);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteVariantAttributeDefinitionOutput> delete(
      @PathVariable UUID id
  ) {
    var input = new DeleteVariantAttributeDefinitionInput(id);
    handler.execute(DeleteVariantAttributeDefinitionAction.class, input);
    return ResponseEntity.noContent().build();
  }
}
