package com.devvision.series.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO implements Serializable {
	private static final long serialVersionUID = 13088666960998787L;
	
	private String email;
	private String password;
}
