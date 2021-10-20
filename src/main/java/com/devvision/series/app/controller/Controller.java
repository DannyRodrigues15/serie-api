package com.devvision.series.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devvision.series.app.core.excepions.RunTimeException;
import com.devvision.series.app.core.utils.EncryptHash;
import com.devvision.series.app.service.TokenService;

public class Controller {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EncryptHash encryptHash;
	
	@Autowired
	TokenService tokenService;
	
	public void verifyToken(String token) {
		if(token == null) {
			throw new RunTimeException("Acesso inválido, cliente não tem chave de autorização.");
		}
		encryptHash.encode("small2009");
		Long idUser = Long.parseLong(encryptHash.decode(token));

		String tokenHash = tokenService.findUserToken(idUser);
		verifyTokenValid(token, tokenHash);
    }
	
	private void verifyTokenValid(String token, String tokenHash) {
		boolean verify = passwordEncoder.matches(token, tokenHash);
		
		if(!verify) {
			throw new RunTimeException("Acesso inválido, cliente não tem autorização.");
		}
	}
}
