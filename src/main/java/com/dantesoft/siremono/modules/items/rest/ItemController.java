package com.dantesoft.siremono.modules.items.rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.items.action.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Tag(
        name = "Items",
        description = "Items management for sales, purchases and invoices in general"
)
public class ItemController {
  private final CommandExecutor handler;

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
                  description = "Default server error with trace")
  })
  public ResponseEntity<ListItemOutput> list(
          @ParameterObject
          @PageableDefault
          Pageable pageable,
          @Parameter(description = "Term to search items by name")
          @RequestParam(required = false)
          String searchParam) {
    var input = new ListItemInput();
    input.setPageable(pageable);
    input.setSearchParam(searchParam);
    var out = handler.execute(ListItemAction.class, input);
    return ResponseEntity.ok(out);
  }

  @GetMapping("/{itemId}")
  @Operation(
          summary = "Find a item",
          description = "Makes a search of the item matches with the id, then return the assoiciated data"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Item found, data retrieved"
          ),
          @ApiResponse(
                  responseCode = "401",
                  description = "Not authorized, try login"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Permission not found"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "Error on the server"
          )
  })
  public ResponseEntity<FindItemOutput> find(
          @Parameter(description = "The id of the item")
          @PathVariable
          UUID itemId
  ) {
    var input = new FindItemInput();
    input.setId(itemId);
    var out = handler.execute(FindItemAction.class, input);

    return ResponseEntity.ok(out);
  }

  @PostMapping
  @Operation(
          summary = "Create a new item",
          description = "Saves a new item with images")
  @ApiResponses({
          @ApiResponse(
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
  @ApiResponses({
          @ApiResponse(
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
  public ResponseEntity<UpdateItemOutput> update(
          @Parameter(description = "ID of the item to update")
          @PathVariable
          UUID itemId,
          @RequestBody
          @Valid
          UpdateItemInput input) {
    input.setId(itemId);
    handler.execute(UpdateItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{itemId}/enable")
  @Operation(
          summary = "Enable an item",
          description = "Enables an item by ID")
  @ApiResponses({
          @ApiResponse(
                  responseCode = "204",
                  description = "Item enabled successfully"),
          @ApiResponse(
                  responseCode = "404",
                  description = "Item not found"),
          @ApiResponse(
                  responseCode = "500",
                  description = "Server error")
  })
  public ResponseEntity<EnableItemOutput> enable(
          @Parameter(description = "The item id to be enabled")
          @PathVariable
          UUID itemId
  ) {
    var input = new EnableItemInput();
    input.setId(itemId);
    handler.execute(EnableItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{itemId}/disable")
  @Operation(
          summary = "Disable an item",
          description = "Disables an item by ID")
  @ApiResponses({
          @ApiResponse(
                  responseCode = "204",
                  description = "Item disabled successfully"),
          @ApiResponse(
                  responseCode = "404",
                  description = "Item not found"),
          @ApiResponse(
                  responseCode = "500",
                  description = "Server error")})
  public ResponseEntity<DisableItemOutput> disable(
          @Parameter(description = "the item id to be disabled")
          @PathVariable
          UUID itemId
  ) {
    var input = new DisableItemInput();
    input.setId(itemId);
    handler.execute(DisableItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{itemId}")
  @Operation(
          summary = "Delete an item",
          description = "Deletes an item by ID")
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Item deleted successfully"),
          @ApiResponse(
                  responseCode = "404",
                  description = "Item not found"),
          @ApiResponse(
                  responseCode = "500",
                  description = "Server error")
  })
  public ResponseEntity<Void> delete(
          @Parameter(description = "The item id to delete")
          @PathVariable
          UUID itemId
  ) {
    var input = new DeleteItemInput();
    input.setId(itemId);
    handler.execute(DeleteItemAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/delete")
  @Operation(
          summary = "Delete multiple items",
          description = "Deletes multiple items")
  public ResponseEntity<Void> deleteMany(
          @RequestBody
          DeleteManyItemsInput input
  ) {
    handler.execute(DeleteManyItemsAction.class, input);
    return ResponseEntity.noContent().build();
  }
}
