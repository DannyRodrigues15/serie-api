package com.devvision.series.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name="seq_id_episode", sequenceName="seq_id_episode", allocationSize=1, initialValue=1)
@Table(name = "episodes",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_EPISODE"))
public class Episode implements Serializable {
	private static final long serialVersionUID = 2456358793213654L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_id_episode")
	private Long id;
	
	@Column(name="imdb_identifier")
	private String imdbIdentifier;
	
	@Column(name="season_number")
	private int seasonNumber;
	
	@Column(name="episode_number")
	private int episodeNumber;
	
	private String title;
	
	
	
}
