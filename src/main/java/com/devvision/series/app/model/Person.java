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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name="seq_id_person", sequenceName="seq_id_person", allocationSize=1, initialValue=1)
@Table(name = "persons",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_PERSON"))
public class Person implements Serializable {
	private static final long serialVersionUID = 1487698564544444L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_person")
	private Long id;
	
	@NotBlank
	@Size(max=60)
	@Column(nullable=true)
	private String name;

	@NotBlank
	@Size(max=120)
	@Column(name="last_name", nullable=true)
	private String lastName;
	
	@NotBlank
	@Email(message="É obrigatório um e-mail válido")
	@Size(max = 130)
	@Column(nullable=true)
	private String email;
	
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
