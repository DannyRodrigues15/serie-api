        package com.devvision.series.app.dto;

import java.io.Serializable;
import java.util.List;

import com.devvision.series.app.model.Group;
import com.devvision.series.app.model.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 13545455667711228L;
	
	private boolean active;
	private String password;
	private Person person;
	private List<Group> groups;
}
