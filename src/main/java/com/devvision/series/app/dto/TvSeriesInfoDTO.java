package com.devvision.series.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesInfoDTO implements Serializable {
	private static final long serialVersionUID = 546467982213535678L;
	
	private String creators;
	private List<Object> seasons;

}
