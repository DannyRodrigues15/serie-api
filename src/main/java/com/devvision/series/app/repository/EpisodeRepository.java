package com.devvision.series.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.devvision.series.app.model.Episode;

public interface EpisodeRepository extends PagingAndSortingRepository<Episode, Long> {

	@Modifying
	@Query(value = "SELECT * FROM episodes WHERE id_serie=:id_serie AND season_number=:season_number ORDER BY episode_number ASC", 
	nativeQuery = true)
	public List<Episode> findAllEpisodeSeason(@Param("id_serie") Long id_serie, @Param("season_number") int season_number);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO episodes (id, episode_number, season_number, title, imdb_identifier, id_serie) "
			+ "VALUES (nextval('seq_id_episode'), :episode_number, :season_number, :title, :imdb_identifier, :id_serie)", 
		nativeQuery = true)
	public void insertOneEpisode(
			@Param("episode_number") int episode_number,
			@Param("season_number") int season_number,
			@Param("title") String title,
			@Param("imdb_identifier") String imdb_identifier,
			@Param("id_serie") Long id_serie);
	
}
