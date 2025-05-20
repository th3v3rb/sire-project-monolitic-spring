package com.dantesoft.siremono.modules.items.rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.items.brands.actions.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/brands")
@RequiredArgsConstructor
@Tag(
		name = "Brand",
		description = "Brands management for items segregation")
public class BrandController {
	private final CommandExecutor handler;

	@GetMapping
	@Operation(
			summary = "List brands",
			description = "Retrieve a paginated list of brands",
			responses = { @ApiResponse(
					responseCode = "200",
					description = "Brands retrieved successfully"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	// @PreAuthorize("hasAuthority('view:brands')")
	public ResponseEntity<ListBrandOutput> list(@PageableDefault @ParameterObject Pageable pageable,
			@RequestParam(
					required = false) @Parameter(
							description = "Search by brand name") String searchParam,
			@RequestParam(
					required = false) @Parameter(
							description = "Filter by active/inactive status") Boolean searchStatusParam) {

		var input = new ListBrandInput();
		input.setPageable(pageable);
		input.setSearchParam(searchParam);

		var out = handler.execute(ListBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PostMapping
	@Operation(
			summary = "Save brand",
			description = "Persist a new brand in the database",
			responses = { @ApiResponse(
					responseCode = "201",
					description = "Brand created successfully"),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	public ResponseEntity<SaveBrandOutput> save(@RequestBody @Valid SaveBrandInput input) {
		var out = handler.execute(SaveBrandAction.class, input);
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}

	@PatchMapping("/{id}")
	@Operation(
			summary = "Update brand",
			description = "Update the brand identified by the provided id",
			responses = { @ApiResponse(
					responseCode = "200",
					description = "Brand updated successfully"),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error"),
					@ApiResponse(
							responseCode = "404",
							description = "Brand not found"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	public ResponseEntity<UpdateBrandOutput> update(@PathVariable @Parameter(
			description = "Brand id") UUID id,
			@RequestBody @Valid UpdateBrandInput input) {
		input.setId(id);
		var out = handler.execute(UpdateBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PatchMapping("/{id}/enable")
	@Operation(
			summary = "Enable brand",
			description = "Enable the brand identified by the provided id",
			responses = { @ApiResponse(
					responseCode = "200",
					description = "Brand enabled successfully"),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error"),
					@ApiResponse(
							responseCode = "404",
							description = "Brand not found"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	public ResponseEntity<EnableBrandOutput> enable(@PathVariable @Parameter(
			description = "Brand id") UUID id) {
		var input = new EnableBrandInput();
		input.setId(id);
		var out = handler.execute(EnableBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@PatchMapping("/{id}/disable")
	@Operation(
			summary = "Disable brand",
			description = "Disable the brand identified by the provided id",
			responses = { @ApiResponse(
					responseCode = "200",
					description = "Brand disabled successfully"),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error"),
					@ApiResponse(
							responseCode = "404",
							description = "Brand not found"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	public ResponseEntity<DisableBrandOutput> disable(@PathVariable @Parameter(
			description = "Brand id") UUID id) {
		var input = new DisableBrandInput();
		input.setId(id);
		var out = handler.execute(DisableBrandAction.class, input);
		return ResponseEntity.ok(out);
	}

	@DeleteMapping("/{id}")
	@Operation(
			summary = "Delete brand",
			description = "Delete the brand identified by the provided id",
			responses = { @ApiResponse(
					responseCode = "204",
					description = "Brand deleted successfully, no content returned"),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error"),
					@ApiResponse(
							responseCode = "404",
							description = "Brand not found"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	public ResponseEntity<DeleteBrandOutput> delete(@PathVariable @Parameter(
			description = "Brand id to be deleted") UUID id) {
		var input = new DeleteBrandInput();
		input.setId(id);
		handler.execute(DeleteBrandAction.class, input);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/delete")
	@Operation(
			summary = "Delete multiple brands",
			description = "Delete multiple brands corresponding to the provided ids",
			responses = { @ApiResponse(
					responseCode = "204",
					description = "Brands deleted successfully, no content returned"),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error"),
					@ApiResponse(
							responseCode = "500",
							description = "Server error") })
	public ResponseEntity<Void> deleteMany(@RequestBody @Valid DeleteManyBrandsInput input) {
		handler.execute(DeleteManyBrandsAction.class, input);
		return ResponseEntity.noContent().build();
	}
}
