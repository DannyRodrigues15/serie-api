package com.devvision.series.app.model;

import static com.devvision.series.app.core.utils.DateUtil.now;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name="seq_id_group", sequenceName="seq_id_group", allocationSize=1, initialValue=1)
@Table(name = "groups",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_GROUP"))
public class Group implements Serializable {
	private static final long serialVersionUID = 8997734544890984L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_group")
	private Long id;
	
	private String type;
	
	private String name;
	
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
