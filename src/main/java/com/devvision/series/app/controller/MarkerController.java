package com.devvision.series.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devvision.series.app.core.utils.ReturnRequest;
import com.devvision.series.app.core.utils.Status;
import com.devvision.series.app.dto.MarkerDTO;
import com.devvision.series.app.interfaces.controller.IController;
import com.devvision.series.app.service.MarkerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/markers")
public class MarkerController extends Controller implements IController<MarkerDTO> {
	@Autowired 
	private MarkerService service;
	
	@Autowired
	private Status status;
	
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping
	public ReturnRequest findAll(@RequestHeader Map<String, String> headers, Pageable pageable) {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.findAll(pageable);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@GetMapping("/{id}")
	public ReturnRequest findOne(@RequestHeader Map<String, String> headers, @PathVariable Long id) {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.findOne(id);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}

	@PostMapping
	public ReturnRequest insert(@RequestHeader Map<String, String> headers, @Valid @RequestBody MarkerDTO dto) {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.insert(dto);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
		
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@PutMapping("/{id}")
	public ReturnRequest update(@RequestHeader Map<String, String> headers, @Valid @PathVariable Long id, @RequestBody MarkerDTO dto) {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.update(id, dto);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@DeleteMapping("/{id}")
	public ReturnRequest delete(@RequestHeader Map<String, String> headers, @PathVariable Long id) {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.delete(id);
			
			ResponseEntity.ok();
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@PutMapping("/update-episode/{id}")
	public ReturnRequest updateEpisode(@RequestHeader Map<String, String> headers, @Valid @PathVariable Long id, @RequestBody MarkerDTO dto) {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.updateEpisode(id, dto);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
}
