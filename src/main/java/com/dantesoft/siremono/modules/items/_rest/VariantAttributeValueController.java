package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.variantattributevalue.actions.ListVariantAttributeValuesAction;
import com.dantesoft.siremono.modules.items.variantattributevalue.actions.ListVariantAttributeValuesInput;
import com.dantesoft.siremono.modules.items.variantattributevalue.actions.ListVariantAttributeValuesOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/variants/attributes/{attributeId}/values")
@RequiredArgsConstructor
public class VariantAttributeValueController {
  private final CommandExecutor handler;

  @GetMapping
  public ResponseEntity<ListVariantAttributeValuesOutput> list(
      @PathVariable UUID attributeId,
      @PageableDefault Pageable pageable
  ) {
    var input = new ListVariantAttributeValuesInput(
        attributeId,
        pageable
    );

    var output = handler.execute(
        ListVariantAttributeValuesAction.class,
        input
    );

    return ResponseEntity.ok(output);
  }
}
