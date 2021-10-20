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
public class ChangePasswordDTO implements Serializable {
	private static final long serialVersionUID = 6549043309998787L;
	
	private String password;
	private String oldPassword;
}
