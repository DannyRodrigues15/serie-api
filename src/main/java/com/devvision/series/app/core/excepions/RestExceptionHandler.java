package com.devvision.series.app.core.excepions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devvision.series.app.core.utils.Dump;
import com.devvision.series.app.core.utils.ReturnRequest;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
		Logger logger = LoggerFactory.getLogger(RunTimeException.class);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(0)
				.status(500)
				.data(HttpStatus.INTERNAL_SERVER_ERROR)
				.errorMessage(e.getLocalizedMessage())
				.build();
		
		logger.error("Message resultRequest: " + resultRequest + " | Exception: " + e + 
				" | Error RestTimeException: " + this.getClass() + " - Line: " + Dump.getLineNumber());

		// pode colocar a exeção e no lugar de resultRequest para ver todo o stack trace
		return new ResponseEntity<>(resultRequest, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
