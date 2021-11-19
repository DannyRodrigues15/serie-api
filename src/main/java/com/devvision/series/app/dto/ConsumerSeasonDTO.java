package com.devvision.series.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerSeasonDTO implements Serializable {
	private static final long serialVersionUID = 546137245213535678L;

	private TvSeriesInfoDTO tvSeriesInfo;
	
}
