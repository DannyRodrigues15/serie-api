package com.devvision.series.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedDTO implements Serializable {
	private static final long serialVersionUID = 143334322111227L;
	
	private String idUser;
	private String name;
	private String lastName;
	private String idToken;
	private String token;
	private List<String> permissions;
}
