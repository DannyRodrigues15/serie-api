package com.devvision.series.app.model;

import static com.devvision.series.app.core.utils.DateUtil.now;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
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
@SequenceGenerator(name="seq_id_user", sequenceName="seq_id_user", allocationSize=1, initialValue=1)
@Table(name = "users",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_USER"))
public class User implements Serializable {
	private static final long serialVersionUID = 89876875454443434L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_user")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_person", referencedColumnName="id", foreignKey=@ForeignKey(name="FK_TB_PERSONS_IN_TB_USERS"))
	private Person person;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_has_groups",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey=@ForeignKey(name="FK_TB_USERS_IN_TB_USER_HAS_GROUPS")),
        inverseJoinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id", foreignKey=@ForeignKey(name="FK_TB_GROUPS_IN_TB_USER_HAS_GROUPS")))
    private List<Group> groups;
	
	private boolean active;
	
	@NotBlank
	@Size(max = 255)
	private String password;
	
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
