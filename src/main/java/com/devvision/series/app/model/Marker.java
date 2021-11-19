package com.devvision.series.app.model;

import static com.devvision.series.app.core.utils.DateUtil.now;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@SequenceGenerator(name="seq_id_marker", sequenceName="seq_id_marker", allocationSize=1, initialValue=1)
@Table(name = "markers",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_MARKER"))
public class Marker implements Serializable {
	private static final long serialVersionUID = 2134659785612321L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_id_marker")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_user", referencedColumnName="id", foreignKey=@ForeignKey(name="FK_TB_USERS_IN_TB_MARKERS"))
	private User user;

	@ManyToOne
	@JoinColumn(name="id_serie", referencedColumnName="id", foreignKey=@ForeignKey(name="FK_TB_SERIES_IN_TB_MARKERS"))
	private Serie serie;
	
	@ManyToOne
	@JoinColumn(name="id_episode", referencedColumnName="id", foreignKey=@ForeignKey(name="FK_TB_EPISODES_IN_TB_MARKERS"))
	private Episode episode;
	
	@Column(name="new_episode")
	private boolean newEpisode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_date", updatable=false)
	private Date registerDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_update")
	private Date registerUpdate;
	
	@PrePersist
    protected void prePersist() {
        registerDate = now();
        registerUpdate = now();
        
    }
	
	@PreUpdate
    protected void preUpdate() {
		registerUpdate = now();
    }
	
}
