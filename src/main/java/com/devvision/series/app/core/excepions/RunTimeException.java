package com.devvision.series.app.core.excepions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.devvision.series.app.core.utils.Dump;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunTimeException extends RuntimeException {
	Logger logger = LoggerFactory.getLogger(RunTimeException.class);
	private static final long serialVersionUID = 1L;

	public RunTimeException(String message) {
		super("Erro código: " + serialVersionUID + ". Mensagem: " + message);
		logger.error("Message: " + message + 
				" | Error RunTimeException: " + this.getClass() + " - Line: " + Dump.getLineNumber());
	}	
}