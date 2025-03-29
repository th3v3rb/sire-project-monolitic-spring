package com.dantesoft.siremono.modules.items.rest;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dantesoft.siremono.internal.actions.io.AbstractRest;
import com.dantesoft.siremono.modules.items.items.action.DeleteItemAction;
import com.dantesoft.siremono.modules.items.items.action.DeleteManyItemsAction;
import com.dantesoft.siremono.modules.items.items.action.DeleteManyItemsInput;
import com.dantesoft.siremono.modules.items.items.action.DeleteManyItemsOutput;
import com.dantesoft.siremono.modules.items.items.action.DeleteItemInput;
import com.dantesoft.siremono.modules.items.items.action.DeleteItemOutput;
import com.dantesoft.siremono.modules.items.items.action.DisableItemAction;
import com.dantesoft.siremono.modules.items.items.action.DisableManyItemsAction;
import com.dantesoft.siremono.modules.items.items.action.DisableManyItemsInput;
import com.dantesoft.siremono.modules.items.items.action.DisableManyItemsOutput;
import com.dantesoft.siremono.modules.items.items.action.DisableItemInput;
import com.dantesoft.siremono.modules.items.items.action.DisableItemOutput;
import com.dantesoft.siremono.modules.items.items.action.EnableItemAction;
import com.dantesoft.siremono.modules.items.items.action.EnableManyItemsAction;
import com.dantesoft.siremono.modules.items.items.action.EnableManyItemsInput;
import com.dantesoft.siremono.modules.items.items.action.EnableManyItemsOutput;
import com.dantesoft.siremono.modules.items.items.action.EnableItemInput;
import com.dantesoft.siremono.modules.items.items.action.EnableItemOutput;
import com.dantesoft.siremono.modules.items.items.action.ListItemAction;
import com.dantesoft.siremono.modules.items.items.action.ListItemInput;
import com.dantesoft.siremono.modules.items.items.action.ListItemOutput;
import com.dantesoft.siremono.modules.items.items.action.SaveItemAction;
import com.dantesoft.siremono.modules.items.items.action.SaveItemInput;
import com.dantesoft.siremono.modules.items.items.action.SaveItemOutput;
import com.dantesoft.siremono.modules.items.items.action.UpdateItemAction;
import com.dantesoft.siremono.modules.items.items.action.UpdateItemInput;
import com.dantesoft.siremono.modules.items.items.action.UpdateItemOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Tag(
    name = "Items",
    description = "Items management for sales, purchases and invoices in general")
public class ItemController extends AbstractRest {

  @GetMapping
  @Operation(
      summary = "List paginated items",
      description = "Returns a paginated list of items")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Returns a page of items"),
      @ApiResponse(
          responseCode = "400",
          description = "Validation error, review the args"),
      @ApiResponse(
          responseCode = "500",
          description = "Default server error with trace") })
  public ResponseEntity<ListItemOutput> list(
      @ParameterObject @PageableDefault Pageable pageable,
      @Parameter(description = "Term to search items by name")
      @RequestParam(required = false) String searchParam) {
    var input = new ListItemInput();
    input.setPageable(pageable);
    input.setSearchParam(searchParam);
    return handle(ListItemAction.class, input);
  }

  @PostMapping
  @Operation(
      summary = "Create a new item",
      description = "Saves a new item in the database")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Item created successfully"),
      @ApiResponse(responseCode = "400", description = "Validation error"),
      @ApiResponse(responseCode = "500", description = "Server error") })
  public ResponseEntity<SaveItemOutput> save(
      @Valid @RequestBody SaveItemInput input) {
    return handle(SaveItemAction.class, input);
  }

  @PutMapping("/{itemId}")
  @Operation(
      summary = "Update an item",
      description = "Updates an existing item with the given ID")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Item updated successfully"),
      @ApiResponse(responseCode = "400", description = "Validation error"),
      @ApiResponse(responseCode = "404", description = "Item not found"),
      @ApiResponse(responseCode = "500", description = "Server error") })
  public ResponseEntity<UpdateItemOutput> update(
      @Parameter(description = "ID of the item to update")
      @PathVariable UUID itemId,
      @Valid @RequestBody UpdateItemInput input) {
    input.setId(itemId);
    return handle(UpdateItemAction.class, input);
  }

  @PatchMapping("/{itemId}/enable")
  @Operation(summary = "Enable an item", description = "Enables an item by ID")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Item enabled successfully"),
      @ApiResponse(responseCode = "404", description = "Item not found"),
      @ApiResponse(responseCode = "500", description = "Server error") })
  public ResponseEntity<EnableItemOutput> enable(
      @Parameter(description = "The item id to be enabled")
      @PathVariable UUID itemId) {
    var input = new EnableItemInput();
    input.setId(itemId);
    return handle(EnableItemAction.class, input);
  }

  @PatchMapping("/enable/batch")
  @Operation(
      summary = "Enable multiple items",
      description = "Enables multiple items")
  public ResponseEntity<EnableManyItemsOutput> enableMany(
      @RequestBody EnableManyItemsInput input) {
    return handle(EnableManyItemsAction.class, input);
  }

  @PatchMapping("/{itemId}/disable")
  @Operation(
      summary = "Disable an item",
      description = "Disables an item by ID")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Item disabled successfully"),
      @ApiResponse(responseCode = "404", description = "Item not found"),
      @ApiResponse(responseCode = "500", description = "Server error") })
  public ResponseEntity<DisableItemOutput> disable(
      @Parameter(description = "the item id to be disabled")
      @PathVariable UUID itemId) {
    var input = new DisableItemInput();
    input.setId(itemId);
    return handle(DisableItemAction.class, input);
  }

  @PatchMapping("/disable/batch")
  @Operation(
      summary = "Disable multiple items",
      description = "Disables multiple items")
  public ResponseEntity<DisableManyItemsOutput> disableMany(
      @RequestBody DisableManyItemsInput input) {
    var out = handler.execute(DisableManyItemsAction.class, input);
    return ResponseEntity.ok(out);
  }

  @DeleteMapping("/{itemId}")
  @Operation(summary = "Delete an item", description = "Deletes an item by ID")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Item deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Item not found"),
      @ApiResponse(responseCode = "500", description = "Server error") })
  public ResponseEntity<DeleteItemOutput> delete(
      @Parameter(description = "The item id to delete")
      @PathVariable UUID itemId) {
    var input = new DeleteItemInput();
    input.setId(itemId);
    var out = handler.execute(DeleteItemAction.class, input);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(out);
  }

  @DeleteMapping("/batch")
  @Operation(
      summary = "Delete multiple items",
      description = "Deletes multiple items")
  public ResponseEntity<DeleteManyItemsOutput> deleteMany(
      @RequestBody DeleteManyItemsInput input) {
    return handle(DeleteManyItemsAction.class, input);
  }
}
