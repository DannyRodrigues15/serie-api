package com.devvision.series.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devvision.series.app.model.Serie;

public interface SerieRepository extends PagingAndSortingRepository<Serie, Long> {
	
	

}
