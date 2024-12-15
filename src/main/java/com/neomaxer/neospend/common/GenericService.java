package com.neomaxer.neospend.common;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.exception.NotFoundException;
import com.neomaxer.neospend.models.common.BaseEntity;



@Service
public abstract class GenericService<T extends BaseEntity> {

	private final GenericRepository<T> repository;

	public GenericService(GenericRepository<T> repository) {
		this.repository = repository;
	}	
	

	public Page<T> getPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public T get(UUID id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
	}

	@Transactional
	public T update(T updated) {
//		TODO Copy old to new
		return repository.save(updated);
	}

	@Transactional
	public T create(T entity) {
		return repository.save(entity);
	}

	@Transactional
	public void delete(UUID id) {
		// check if object with this id exists
		get(id);
		repository.deleteById(id);
	}
}
