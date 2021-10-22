package com.devvision.series.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@SequenceGenerator(name="seq_id_serie", sequenceName="seq_id_serie", allocationSize=1, initialValue=1)
@Table(name = "series",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_SERIE"))
public class Serie implements Serializable {
	private static final long serialVersionUID = 5265448877627420L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_id_serie")
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_serie", referencedColumnName="id", foreignKey=@ForeignKey(name="FK_TB_SERIES_IN_TB_EPISODES"))
	private List<Episode> episodes;
	
	@Column(name="imdb_identifier")
	private String imdbIdentifier;
	
	private String image;
	
	private String title;
	
	private String type;
	
	private int year;
	
	@Column(name="runtime_str")
	private String runtimeStr;
	
	@Column(name="plot_local")
	private String plotLocal;
	
	private String stars;
	
	private String genres;
	
	@Column(name="imdb_rating")
	private float imDbRating;
	
	private String creators;
	
	private int seasons;
	
	
}
