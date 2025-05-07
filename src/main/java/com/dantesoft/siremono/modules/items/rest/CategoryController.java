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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.categories.action.DeleteCategoryAction;
import com.dantesoft.siremono.modules.items.categories.action.DeleteCategoryInput;
import com.dantesoft.siremono.modules.items.categories.action.DeleteManyCategoriesAction;
import com.dantesoft.siremono.modules.items.categories.action.DeleteManyCategoriesInput;
import com.dantesoft.siremono.modules.items.categories.action.DisableCategoryAction;
import com.dantesoft.siremono.modules.items.categories.action.DisableCategoryInput;
import com.dantesoft.siremono.modules.items.categories.action.DisableCategoryOutput;
import com.dantesoft.siremono.modules.items.categories.action.EnableCategoryAction;
import com.dantesoft.siremono.modules.items.categories.action.EnableCategoryInput;
import com.dantesoft.siremono.modules.items.categories.action.EnableCategoryOutput;
import com.dantesoft.siremono.modules.items.categories.action.ListCategoryAction;
import com.dantesoft.siremono.modules.items.categories.action.ListCategoryInput;
import com.dantesoft.siremono.modules.items.categories.action.ListCategoryOutput;
import com.dantesoft.siremono.modules.items.categories.action.SaveCategoryAction;
import com.dantesoft.siremono.modules.items.categories.action.SaveCategoryInput;
import com.dantesoft.siremono.modules.items.categories.action.SaveCategoryOutput;
import com.dantesoft.siremono.modules.items.categories.action.UpdateCategoryAction;
import com.dantesoft.siremono.modules.items.categories.action.UpdateCategoryInput;
import com.dantesoft.siremono.modules.items.categories.action.UpdateCategoryOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/items/categories")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Management of product categories")
public class CategoryController {
	private final CommandExecutor handler;

	@GetMapping
	@Operation(summary = "List categories", description = "Retrieve a paginated list of categories", responses = {
			@ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<ListCategoryOutput> list(@PageableDefault @ParameterObject Pageable pageable,
			@RequestParam(required = false) @Parameter(description = "Search by category name") String searchParam) {

		var input = new ListCategoryInput();
		input.setPageable(pageable);
		input.setSearchParam(searchParam);
		var output = handler.execute(ListCategoryAction.class, input);
		return ResponseEntity.ok(output);
	}

	@PostMapping
	@Operation(summary = "Save category", description = "Persist a new category in the database", responses = {
			@ApiResponse(responseCode = "200", description = "Category created successfully"),
			@ApiResponse(responseCode = "400", description = "Validation error"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<SaveCategoryOutput> save(@Valid @RequestBody SaveCategoryInput input) {
		var output = handler.execute(SaveCategoryAction.class, input);
		return ResponseEntity.ok(output);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Update category", description = "Update the category identified by the provided id", responses = {
			@ApiResponse(responseCode = "200", description = "Category updated successfully"),
			@ApiResponse(responseCode = "400", description = "Validation error"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<UpdateCategoryOutput> update(@PathVariable @Parameter(description = "Category id") UUID id,
			@Valid @RequestBody UpdateCategoryInput input) {
		input.setId(id);
		var output = handler.execute(UpdateCategoryAction.class, input);
		return ResponseEntity.ok(output);
	}

	@PatchMapping("/{id}/enable")
	@Operation(summary = "Enable category", description = "Enable the category identified by the provided id", responses = {
			@ApiResponse(responseCode = "200", description = "Category enabled successfully"),
			@ApiResponse(responseCode = "400", description = "Validation error"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<EnableCategoryOutput> enable(@PathVariable @Parameter(description = "Category id") UUID id) {
		var input = new EnableCategoryInput();
		input.setId(id);
		var output = handler.execute(EnableCategoryAction.class, input);
		return ResponseEntity.ok(output);
	}

	@PatchMapping("/{id}/disable")
	@Operation(summary = "Disable category", description = "Disable the category identified by the provided id", responses = {
			@ApiResponse(responseCode = "200", description = "Category disabled successfully"),
			@ApiResponse(responseCode = "400", description = "Validation error"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<DisableCategoryOutput> disable(
			@PathVariable @Parameter(description = "Category id") UUID id) {
		var input = new DisableCategoryInput();
		input.setId(id);
		var output = handler.execute(DisableCategoryAction.class, input);
		return ResponseEntity.ok(output);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete category", description = "Delete the category identified by the provided id", responses = {
			@ApiResponse(responseCode = "200", description = "Category deleted successfully, no content returned"),
			@ApiResponse(responseCode = "400", description = "Validation error"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Category id") UUID id) {
		var input = new DeleteCategoryInput();
		input.setId(id);
		handler.execute(DeleteCategoryAction.class, input);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/delete")
	@Operation(summary = "Delete multiple categories", description = "Delete multiple categories corresponding to the provided ids", responses = {
			@ApiResponse(responseCode = "204", description = "Categories deleted successfully, no content returned"),
			@ApiResponse(responseCode = "400", description = "Validation error"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	public ResponseEntity<Void> deleteMany(@Valid @RequestBody DeleteManyCategoriesInput input) {
		handler.execute(DeleteManyCategoriesAction.class, input);
		return ResponseEntity.noContent().build();
	}
}
