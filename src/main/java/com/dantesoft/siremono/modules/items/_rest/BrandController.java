package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.brands.actions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/brands")
@RequiredArgsConstructor
public class BrandController {
	private final CommandExecutor handler;

	@GetMapping
	// @PreAuthorize("hasAuthority('view:brands')")
	public ResponseEntity<ListBrandOutput> list(@PageableDefault Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchParam) {
		var input = new ListBrandInput();
		input.setPageable(pageable);
		input.setSearchParam(searchParam);

		var out = handler.execute(ListBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PostMapping
	public ResponseEntity<SaveBrandOutput> save(@RequestBody @Valid SaveBrandInput input) {
		var out = handler.execute(SaveBrandAction.class, input);
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UpdateBrandOutput> update(@PathVariable UUID id, @RequestBody @Valid UpdateBrandInput input) {
		input.setId(id);
		var out = handler.execute(UpdateBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PatchMapping("/{id}/enable")
	public ResponseEntity<EnableBrandOutput> enable(@PathVariable UUID id) {
		var input = new EnableBrandInput();
		input.setId(id);
		var out = handler.execute(EnableBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PatchMapping("/{id}/disable")
	public ResponseEntity<DisableBrandOutput> disable(@PathVariable UUID id) {
		var input = new DisableBrandInput();
		input.setId(id);
		var out = handler.execute(DisableBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteBrandOutput> delete(@PathVariable UUID id) {
		var input = new DeleteBrandInput();
		input.setId(id);
		handler.execute(DeleteBrandAction.class, input);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMany(@RequestBody @Valid DeleteManyBrandsInput input) {
		handler.execute(DeleteManyBrandsAction.class, input);
		return ResponseEntity.noContent().build();
	}
}
