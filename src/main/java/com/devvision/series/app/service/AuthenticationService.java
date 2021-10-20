package com.devvision.series.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devvision.series.app.core.excepions.RunTimeException;
import com.devvision.series.app.core.utils.EncryptHash;
import com.devvision.series.app.core.utils.ReturnRequest;
import com.devvision.series.app.core.utils.Status;
import com.devvision.series.app.dto.AuthenticatedDTO;
import com.devvision.series.app.dto.AuthenticationDTO;
import com.devvision.series.app.model.Token;
import com.devvision.series.app.model.User;
import com.devvision.series.app.repository.TokenRepository;
import com.devvision.series.app.repository.UserRepository;

@Service
public class AuthenticationService {
	private ModelMapper modelMapper;
	
	public AuthenticationService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Status status;
	
	@Autowired
	EncryptHash encryptHash;
	
	@Transactional
	public ReturnRequest disableAuthentication(Long id) {
		if (!tokenRepository.existsById(id)) {
			throw new RunTimeException("Autenticação não existe na base de dados.");
		}
		
		tokenRepository.disableAuthentication(id);
	
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Autenticação desativada com sucesso")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest createAuthentication(AuthenticationDTO dto) {
		List<User> result = findUserByEmail(dto.getEmail());
		
		User user = this.modelMapper.map(result.get(0), User.class);
		
		dto.setPassword(encryptHash.decode(dto.getPassword()));
		
		verifyPasswordValid(dto.getPassword(), user.getPassword());
		
		AuthenticatedDTO authenticated = getAuth(user);
		
		//authenticated.se
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1L)
				.successMessage("Autenticação efetuada com sucesso")
				.data(authenticated)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	

	private AuthenticatedDTO getAuth(User user) {
		Token tokenAdded = insertToken(user.getId());
		
		List<String> permissions = user.getGroups()
				.stream().map(name -> {
					return encryptHash.encode(name.getType());
				})
				.collect(Collectors.toList());
		
		 AuthenticatedDTO authenticated = AuthenticatedDTO.builder()
				.idUser(encryptHash.encode(user.getId().toString()))
				.name(user.getPerson().getName())
				.lastName(user.getPerson().getLastName())
				.idToken(encryptHash.encode(tokenAdded.getId().toString()))
				.token(tokenAdded.getToken())
				.permissions(permissions)
				.build();
		 
		return authenticated;
	}
	
	private Token insertToken(Long idUser) {
		String idEncryptedForToken = encryptHash.encode(idUser.toString());
		String tokenHash = encryptHash.getHash(idEncryptedForToken);
		
		User user = new User();
		user.setId(idUser);
		
		Token token = Token.builder()
				.active(true)
				.token(tokenHash)
				.ip("0")
				.user(user)
				.build();
		
		tokenRepository.save(token);
		token.setToken(idEncryptedForToken);
		
		return token;
	}
	
	private List<User> findUserByEmail(String email) {
		List<User> result = userRepository.findUserByEmail(email);
		
		if(result.size() == 0) {
			throw new RunTimeException("Login ou senha inválido.");
		}
		
		return result;
	}
	
	public void verifyPasswordValid(String password, String passwordHash) {
		boolean result = encryptHash.compareValueHash(password, passwordHash);
		
		if(!result) {
			throw new RunTimeException("Login ou senha inválido.");
		}
	}
}
