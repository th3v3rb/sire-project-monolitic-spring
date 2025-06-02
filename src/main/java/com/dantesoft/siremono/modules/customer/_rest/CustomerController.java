package com.dantesoft.siremono.modules.customer._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.customer.customer.actions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CommandExecutor handler;


  @GetMapping
  public ResponseEntity<ListCustomersOutput> list(
      @PageableDefault final Pageable pageable,
      @RequestParam(required = false, defaultValue = "") final String filter
  ) {
    var input = new ListCustomersInput();
    input.setFilter(filter);
    input.setPageable(pageable);

    var out = handler.execute(ListCustomersAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PostMapping
  public ResponseEntity<AddCustomerOutput> save(
      @RequestBody
      @Valid final AddCustomerInput input
  ) {
    var out = handler.execute(AddCustomerAction.class, input);
    return ResponseEntity.ok(out);
  }
}
