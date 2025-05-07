package com.dantesoft.siremono.modules.items.items.store;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemImageService {

	private final ItemImageRepository repository;
	
	public ItemImageEntity save(ItemImageEntity entity) {
		return repository.save(entity);
	}
	
	public Optional<ItemImageEntity> findMainByItem(ItemEntity item) {
		return repository.findMainByItem(item);
	}
	
	public void delete(ItemImageEntity entity) {
		repository.delete(entity);
	}
	
}
