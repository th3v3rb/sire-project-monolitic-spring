package com.dantesoft.siremono.modules.items.rest;

import java.util.UUID;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.items.action.DeleteItemAction;
import com.dantesoft.siremono.modules.items.items.action.DeleteItemInput;
import com.dantesoft.siremono.modules.items.items.action.DeleteManyItemsAction;
import com.dantesoft.siremono.modules.items.items.action.DeleteManyItemsInput;
import com.dantesoft.siremono.modules.items.items.action.DisableItemAction;
import com.dantesoft.siremono.modules.items.items.action.DisableItemInput;
import com.dantesoft.siremono.modules.items.items.action.DisableItemOutput;
import com.dantesoft.siremono.modules.items.items.action.EnableItemAction;
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
public class ItemController {
  private final CommandExecutor handler;

  @GetMapping
  @Operation(
    summary = "List paginated items",
    description = "Returns a paginated list of items")
  @ApiResponses({@ApiResponse(
    responseCode = "200",
    description = "Returns a page of items"),
      @ApiResponse(
        responseCode = "400",
        description = "Validation error, review the args"),
      @ApiResponse(
        responseCode = "500",
        description = "Default server error with trace")})
  public ResponseEntity<ListItemOutput> list(@ParameterObject
  @PageableDefault Pageable pageable, @Parameter(
    description = "Term to search items by name")
  @RequestParam(
    required = false) String searchParam) {
    var input = new ListItemInput();
    input.setPageable(pageable);
    input.setSearchParam(searchParam);
    var out = handler.execute(ListItemAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping
  @Operation(
    summary = "Create a new item",
    description = "Saves a new item with images")
  @ApiResponses({@ApiResponse(
    responseCode = "200",
    description = "Item created successfully"),
      @ApiResponse(
        responseCode = "400",
        description = "Validation error"),
      @ApiResponse(
        responseCode = "500",
        description = "Server error")})
  public ResponseEntity<SaveItemOutput> save(@RequestBody SaveItemInput input) {
    var out = handler.execute(SaveItemAction.class, input);
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .replacePath("/api/v1/items/{itemId}")
        .buildAndExpand(out.getPayload().getId())
        .toUri();

    return ResponseEntity.created(location).body(out);
  }

  @PutMapping("/{itemId}")
  @Operation(
    summary = "Update an item",
    description = "Updates an existing item with the given ID")
  @ApiResponses({@ApiResponse(
    responseCode = "200",
    description = "Item updated successfully"),
      @ApiResponse(
        responseCode = "400",
        description = "Validation error"),
      @ApiResponse(
        responseCode = "404",
        description = "Item not found"),
      @ApiResponse(
        responseCode = "500",
        description = "Server error")})
  public ResponseEntity<UpdateItemOutput> update(@Parameter(
    description = "ID of the item to update")
  @PathVariable UUID itemId, @RequestBody
  @Valid UpdateItemInput input) {
    input.setId(itemId);
    handler.execute(UpdateItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{itemId}/enable")
  @Operation(
    summary = "Enable an item",
    description = "Enables an item by ID")
  @ApiResponses({@ApiResponse(
    responseCode = "204",
    description = "Item enabled successfully"),
      @ApiResponse(
        responseCode = "404",
        description = "Item not found"),
      @ApiResponse(
        responseCode = "500",
        description = "Server error")})
  public ResponseEntity<EnableItemOutput> enable(@Parameter(
    description = "The item id to be enabled")
  @PathVariable UUID itemId) {
    var input = new EnableItemInput();
    input.setId(itemId);
    handler.execute(EnableItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{itemId}/disable")
  @Operation(
    summary = "Disable an item",
    description = "Disables an item by ID")
  @ApiResponses({@ApiResponse(
    responseCode = "204",
    description = "Item disabled successfully"),
      @ApiResponse(
        responseCode = "404",
        description = "Item not found"),
      @ApiResponse(
        responseCode = "500",
        description = "Server error")})
  public ResponseEntity<DisableItemOutput> disable(@Parameter(
    description = "the item id to be disabled")
  @PathVariable UUID itemId) {
    var input = new DisableItemInput();
    input.setId(itemId);
    handler.execute(DisableItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{itemId}")
  @Operation(
    summary = "Delete an item",
    description = "Deletes an item by ID")
  @ApiResponses({@ApiResponse(
    responseCode = "200",
    description = "Item deleted successfully"),
      @ApiResponse(
        responseCode = "404",
        description = "Item not found"),
      @ApiResponse(
        responseCode = "500",
        description = "Server error")})
  public ResponseEntity<Void> delete(@Parameter(
    description = "The item id to delete")
  @PathVariable UUID itemId) {
    var input = new DeleteItemInput();
    input.setId(itemId);
    handler.execute(DeleteItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/delete")
  @Operation(
    summary = "Delete multiple items",
    description = "Deletes multiple items")
  public ResponseEntity<Void> deleteMany(@RequestBody DeleteManyItemsInput input) {
    handler.execute(DeleteManyItemsAction.class, input);
    return ResponseEntity.noContent().build();
  }
}
