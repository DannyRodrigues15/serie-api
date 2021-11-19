package com.devvision.series.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devvision.series.app.dto.ConsumerEpisodeDTO;
import com.devvision.series.app.dto.ConsumerSeasonDTO;
import com.devvision.series.app.dto.EpisodeDTO;
import com.devvision.series.app.dto.SerieDTO;

@Service
public class ConsumerService {
	
	public ConsumerSeasonDTO findSeason(String idImdb) {
		
		RestTemplate restTemplate = new RestTemplate();
		ConsumerSeasonDTO consumerSeasonDTO = restTemplate.getForObject("https://imdb-api.com/pt/API/Title/k_il34848s/" + idImdb, ConsumerSeasonDTO.class);
		return consumerSeasonDTO;
	}
	
	public List<EpisodeDTO> findEpisode(String idImdb, int season) {
			
			RestTemplate restTemplate = new RestTemplate();
			ConsumerEpisodeDTO consumerEpisodeDTO = restTemplate.getForObject("https://imdb-api.com/pt/API/SeasonEpisodes/k_il34848s/" + idImdb + "/" + season, ConsumerEpisodeDTO.class);

			return consumerEpisodeDTO.getEpisodes();
	}
	
	public SerieDTO findSeries(String idImdb) {
		
		RestTemplate restTemplate = new RestTemplate();
		SerieDTO consumerSerieDTO = restTemplate.getForObject("https://imdb-api.com/pt/API/Title/k_il34848s/" + idImdb, SerieDTO.class);
		return consumerSerieDTO;
}

	
}
