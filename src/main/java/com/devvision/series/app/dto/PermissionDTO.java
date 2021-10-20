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
public class PermissionDTO implements Serializable {
	private static final long serialVersionUID = 13599043333298787L;
	
	private Long idUser;
	private Long idGroup;
}
