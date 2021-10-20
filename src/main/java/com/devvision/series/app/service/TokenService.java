package com.devvision.series.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.devvision.series.app.core.excepions.RunTimeException;
import com.devvision.series.app.model.Token;
import com.devvision.series.app.repository.TokenRepository;

@Component
public class TokenService {
	@Autowired
	private TokenRepository repository;
	
	public String findUserToken(Long idUser) {
		//Pageable pageable = PageRequest.of(0, 1);
		List<Token> tokens = repository.findUserToken(idUser);
		
		if (tokens == null) {
			throw new RunTimeException("Cliente não pode autenticar para receber informações.");
		}
		
		return tokens.get(0).getToken();
	}
}
