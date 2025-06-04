package com.dantesoft.siremono.modules.items._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.items.action.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
	private final CommandExecutor handler;

	@GetMapping
	public ResponseEntity<ListItemOutput> list(@PageableDefault Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String filter) {
		var input = new ListItemInput();
		input.setPageable(pageable);
		input.setFilter(filter);
		var out = handler.execute(ListItemAction.class, input);
		return ResponseEntity.ok(out);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<FindItemOutput> find(@PathVariable UUID itemId) {
		var input = new FindItemInput();
		input.setId(itemId);
		var out = handler.execute(FindItemAction.class, input);

		return ResponseEntity.ok(out);
	}

	@PostMapping
	public ResponseEntity<SaveItemOutput> save(@RequestBody SaveItemInput input) {
		var out = handler.execute(SaveItemAction.class, input);
		var location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/v1/items/{itemId}")
				.buildAndExpand(out.getPayload().id()).toUri();

		return ResponseEntity.created(location).body(out);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<UpdateItemOutput> update(@PathVariable UUID itemId,
			@RequestBody @Valid UpdateItemInput input) {
		input.setId(itemId);
		var out = handler.execute(UpdateItemAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PatchMapping("/{itemId}/enable")
	public ResponseEntity<EnableItemOutput> enable(@PathVariable UUID itemId) {
		var input = new EnableItemInput();
		input.setId(itemId);
		handler.execute(EnableItemAction.class, input);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{itemId}/disable")
	public ResponseEntity<DisableItemOutput> disable(@PathVariable UUID itemId) {
		var input = new DisableItemInput();
		input.setId(itemId);
		handler.execute(DisableItemAction.class, input);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<Void> delete(@PathVariable UUID itemId) {
		var input = new DeleteItemInput();
		input.setId(itemId);
		handler.execute(DeleteItemAction.class, input);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteMany(@RequestBody DeleteManyItemsInput input) {
		handler.execute(DeleteManyItemsAction.class, input);
		return ResponseEntity.noContent().build();
	}
}
