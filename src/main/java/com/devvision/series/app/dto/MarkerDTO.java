package com.devvision.series.app.dto;

import java.io.Serializable;

import com.devvision.series.app.model.Episode;
import com.devvision.series.app.model.Serie;
import com.devvision.series.app.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerDTO implements Serializable {
	private static final long serialVersionUID = 6451245612386031L;
	
	private Episode episode;
	private Serie serie;
	private User user;

}
