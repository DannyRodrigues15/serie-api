package com.devvision.series.app.core.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ReturnRequest {
	private int success;
	private int status;
	private Long totalResults;
	private int resultsPerPage;
	private int totalPages;
	private int numberOfElements;
	private int page;
	private String successMessage;
	private String errorMessage;
	private List<?> validationsErrosMessage;
	private Object data;
}