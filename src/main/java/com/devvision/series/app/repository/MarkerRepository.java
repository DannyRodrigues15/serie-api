package com.devvision.series.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.devvision.series.app.model.Marker;

public interface MarkerRepository extends PagingAndSortingRepository<Marker, Long> {
	@Modifying
    @Query(value="UPDATE markers SET id_episode=:id_episode, new_episode=false WHERE id=:id", nativeQuery = true)
    public void updateEpisode(@Param("id") Long idMarker, @Param("id_episode") Long idEpisode);
	
	@Modifying
	@Query(value = "SELECT * FROM markers WHERE id_serie=:id_serie", 
	nativeQuery = true)
	public List<Marker> findAllMarkers(@Param("id_serie") Long id_serie);
	
}
