package com.devvision.series.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devvision.series.app.core.utils.ReturnRequest;
import com.devvision.series.app.core.utils.Status;
import com.devvision.series.app.dto.AuthenticationDTO;
import com.devvision.series.app.service.AuthenticationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController extends Controller {
	@Autowired 
	private AuthenticationService service;
	
	@Autowired
	private Status status;
	
	@Autowired
	private HttpServletResponse response;
	
	@PostMapping
	public ReturnRequest createAuthentication(@Valid @RequestBody AuthenticationDTO dto) {
		try {
			ReturnRequest result = service.createAuthentication(dto);
			
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
	public ReturnRequest disableAuthentication(@RequestHeader Map<String, String> headers, @Valid @PathVariable Long id)  {
		try {
			verifyToken(headers.get("token"));
			
			ReturnRequest result = service.disableAuthentication(id);
			
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
