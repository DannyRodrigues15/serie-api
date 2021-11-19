package com.devvision.series.app.dto;

import java.io.Serializable;
import java.util.List;

import com.devvision.series.app.model.Episode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerieDTO implements Serializable {
	private static final long serialVersionUID = 21346597869784612L;

	private String imdbIdentifier;
	private String image;
	private String title;
	private String type;
	private int year;
	private String runtimeStr;
	private String plotLocal;
	private String stars;
	private String genres;
	private float imDbRating;
	private String creators;
	private TvSeriesInfoDTO tvSeriesInfo;
	private List<Episode> episodes;
	
}
