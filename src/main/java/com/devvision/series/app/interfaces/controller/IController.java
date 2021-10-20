package com.devvision.series.app.interfaces.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.devvision.series.app.core.utils.ReturnRequest;

// é o HelpService
public interface IController<D> {
	public ReturnRequest findAll(Map<String, String> headers, Pageable pageable);
	
	public ReturnRequest findOne(Map<String, String> headers, Long id);

	public ReturnRequest insert(Map<String, String> headers, D requestBody);
	
	public ReturnRequest update(Map<String, String> headers, Long id, D requestBody);
	
	public ReturnRequest delete(Map<String, String> headers, Long id);
}
