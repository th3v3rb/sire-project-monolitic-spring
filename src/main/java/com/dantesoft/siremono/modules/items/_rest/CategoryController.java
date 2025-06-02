package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.categories.action.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<ListCategoryOutput> list(
      @PageableDefault
      Pageable pageable,
      @RequestParam(required = false, defaultValue = "")
      String searchParam
  ) {

    var input = new ListCategoryInput();
    input.setPageable(pageable);
    input.setSearchParam(searchParam);
    var output = handler.execute(ListCategoryAction.class, input);
    return ResponseEntity.ok(output);
  }

  @PostMapping
  public ResponseEntity<SaveCategoryOutput> save(@Valid @RequestBody SaveCategoryInput input) {
    var output = handler.execute(SaveCategoryAction.class, input);
    return ResponseEntity.ok(output);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UpdateCategoryOutput> update(
      @PathVariable
      UUID id,
      @Valid @RequestBody
      UpdateCategoryInput input
  ) {
    input.setId(id);
    var output = handler.execute(UpdateCategoryAction.class, input);
    return ResponseEntity.ok(output);
  }

  @PatchMapping("/{id}/enable")
  public ResponseEntity<EnableCategoryOutput> enable(
      @PathVariable UUID id
  ) {
    var input = new EnableCategoryInput();
    input.setId(id);
    var output = handler.execute(EnableCategoryAction.class, input);
    return ResponseEntity.ok(output);
  }

  @PatchMapping("/{id}/disable")
  public ResponseEntity<DisableCategoryOutput> disable(
      @PathVariable UUID id
  ) {
    var input = new DisableCategoryInput();
    input.setId(id);
    var output = handler.execute(DisableCategoryAction.class, input);
    return ResponseEntity.ok(output);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    var input = new DeleteCategoryInput();
    input.setId(id);
    handler.execute(DeleteCategoryAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/delete")
  public ResponseEntity<Void> deleteMany(@Valid @RequestBody DeleteManyCategoriesInput input) {
    handler.execute(DeleteManyCategoriesAction.class, input);
    return ResponseEntity.noContent().build();
  }
}
