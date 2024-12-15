package com.neomaxer.neospend.common;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neomaxer.neospend.models.common.BaseEntity;

@ResponseBody
public abstract class GenericController<T extends BaseEntity> {

	private final GenericService<T> service;

	public GenericController(GenericRepository<T> repository) {
		this.service = new GenericService<T>(repository) {
		};
	}

	@GetMapping("")
	public ResponseEntity<Page<T>> getPage(Pageable pageable) {
		return ResponseEntity.ok(service.getPage(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> getOne(@PathVariable UUID id) {
		return ResponseEntity.ok(service.get(id));
	}

	@PutMapping("")
	public ResponseEntity<T> update(@RequestBody T updated) {
		return ResponseEntity.ok(service.update(updated));
	}

	@PostMapping("")
	public ResponseEntity<T> create(@RequestBody T created) {
		return ResponseEntity.ok(service.create(created));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.ok("{\"message\":\"Deleted\"}");
	}
}