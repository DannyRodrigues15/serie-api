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
public class ConsumerEpisodeDTO implements Serializable {
	private static final long serialVersionUID = 546137245222135678L;

	private List<EpisodeDTO> episodes;
	
}
