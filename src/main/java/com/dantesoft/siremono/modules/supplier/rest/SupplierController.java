package com.dantesoft.siremono.modules.supplier.rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.supplier.supplier.actions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<ListSuppliersOutput> list(
          @PageableDefault Pageable pageable,
          @RequestParam(required = false) String filter
  ) {
    var input = new ListSuppliersInput();
    input.setFilter(filter);
    input.setPageable(pageable);

    var out = handler.execute(ListSuppliersAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping
  public ResponseEntity<AddSupplierOutput> add(
          @RequestBody @Valid AddSupplierInput input
  ) {
    var out = handler.execute(AddSupplierAction.class, input);

    var location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(out.getPayload().getId())
            .toUri();

    return ResponseEntity
            .created(location)
            .body(out);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdateSupplierOutput> update(
          @PathVariable UUID id,
          @RequestBody @Valid UpdateSupplierInput input
  ) {
    input.setId(id);

    var out = handler.execute(
            UpdateSupplierAction.class,
            input
    );

    var location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(out.getPayload().getId())
            .toUri();

    return ResponseEntity.created(location).body(out);
  }

  @PatchMapping("/{id}/enable")
  public ResponseEntity<EnableSupplierOutput> enable(
          @PathVariable UUID id
  ) {
    var input = new EnableSupplierInput();
    input.setId(id);
    var out = handler.execute(
            EnableSupplierAction.class,
            input
    );
    return ResponseEntity.ok(out);
  }

  @PatchMapping("/{id}/disable")
  public ResponseEntity<DisableSupplierOutput> disable(
          @PathVariable UUID id
  ) {
    var input = new DisableSupplierInput();
    input.setId(id);
    var out = handler.execute(
            DisableSupplierAction.class,
            input
    );
    return ResponseEntity.ok(out);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
          @PathVariable UUID id
  ) {
    var input = new DeleteSupplierInput();
    input.setId(id);
    handler.execute(
            DeleteSupplierAction.class,
            input
    );
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/delete")
  public ResponseEntity<Void> deleteAll(
          @RequestBody DeleteManySuppliersInput input
  ) {
    handler.execute(
            DeleteManySuppliersAction.class,
            input
    );
    return ResponseEntity.noContent().build();
  }

}
