package com.devvision.series.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devvision.series.app.dto.EpisodeDTO;
import com.devvision.series.app.model.Episode;
import com.devvision.series.app.model.Marker;
import com.devvision.series.app.model.Serie;
import com.devvision.series.app.repository.EpisodeRepository;
import com.devvision.series.app.repository.MarkerRepository;
import com.devvision.series.app.repository.SerieRepository;

@Service
public class UpdateSeriesService {
	private ModelMapper modelMapper;
	
	public UpdateSeriesService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	private SerieRepository repository;
	
	@Autowired
	private MarkerRepository markerRepository;
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	@Autowired
	private ConsumerService consumerService;
	
	public int updateSeries() {
		Pageable pageable = PageRequest.of(0, 1000);
		List<Serie> seriesApi = repository.findAll(pageable).getContent();
		
		for (Serie serieApi : seriesApi) {
			int seasonNumberImdb = consumerService.findSeason(serieApi.getImdbIdentifier()).getTvSeriesInfo().getSeasons().size();
			
			if(seasonNumberImdb != serieApi.getSeasons()) {
				for(int i = serieApi.getSeasons(); i <= seasonNumberImdb; i++) {
					updateEpisodes(serieApi, i);
					
					serieApi.setSeasons(i);
					repository.save(serieApi);
				}
			} else {
				updateEpisodes(serieApi, seasonNumberImdb);
			}
			
		}
		
		return 1;
	}

	private void updateEpisodes(Serie serieApi, int currentSeason) {
		List<EpisodeDTO> episodesImdb = consumerService.findEpisode(serieApi.getImdbIdentifier(), currentSeason);
		List<Episode> episodesApi = findAllEpisodeSeason(currentSeason, serieApi.getId());
		
		if(episodesApi.size() < episodesImdb.size()) {
			for(int i = episodesApi.size(); i < episodesImdb.size(); i++) {
				
				String imdb = episodesImdb.get(i).getId();
				
				episodesImdb.get(i).setId(null);
				
				Episode entity = this.modelMapper.map(episodesImdb.get(i), Episode.class);
				
				entity.setImdbIdentifier(imdb);
				
				insertEpisode(serieApi, entity);
			}

			markerNewEpisodes(serieApi, true);
		} 
	}

	private void markerNewEpisodes(Serie serieApi, boolean b) {
		List<Marker> markers = markerRepository.findAllMarkers(serieApi.getId());
		
		for (Marker marker : markers) {
			marker.setNewEpisode(b);
			markerRepository.save(marker);
		}
	}

	private void insertEpisode(Serie serieApi, Episode entity) {
		episodeRepository.insertOneEpisode(entity.getEpisodeNumber(), entity.getSeasonNumber(), entity.getTitle(), 
				entity.getImdbIdentifier(), serieApi.getId());
	}
	
	public List<Episode> findAllEpisodeSeason(int seasonNumber, Long idSerie) {
		List<Episode> episodesAll = episodeRepository.findAllEpisodeSeason(idSerie, seasonNumber);
		
		return episodesAll;
	}
	
}
