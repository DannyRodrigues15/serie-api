package com.devvision.series.app.interfaces.service;

import org.springframework.data.domain.Pageable;

import com.devvision.series.app.core.utils.ReturnRequest;

public interface IService<C> {
	public ReturnRequest findAll(Pageable pageable);
	
	public ReturnRequest findOne(Long id);

	public ReturnRequest insert(C requestBody);
	
	public ReturnRequest update(Long id, C requestBody);
	
	public ReturnRequest delete(Long id);
}
