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
public class EpisodeDTO implements Serializable {
	private static final long serialVersionUID = 54613728945135678L;

	private String imdbIdentifier;
	private int seasonNumber;
	private int episodeNumber;
	private String title;
	
}
