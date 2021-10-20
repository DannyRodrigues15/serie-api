package com.devvision.series.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devvision.series.app.core.excepions.RunTimeException;
import com.devvision.series.app.core.utils.Dump;
import com.devvision.series.app.core.utils.EncryptHash;
import com.devvision.series.app.core.utils.ReturnRequest;
import com.devvision.series.app.core.utils.Status;
import com.devvision.series.app.dto.ChangePasswordDTO;
import com.devvision.series.app.dto.PermissionDTO;
import com.devvision.series.app.dto.UserDTO;
import com.devvision.series.app.interfaces.service.IService;
import com.devvision.series.app.model.Group;
import com.devvision.series.app.model.User;
import com.devvision.series.app.repository.UserRepository;

@Service
public class UserService implements IService<UserDTO> {
	private ModelMapper modelMapper;
	
	public UserService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private Status status;
	
	@Autowired
	EncryptHash encryptHash;

	public ReturnRequest findAll(Pageable pageable) {
		Page<User> result = repository.findAll(pageable);

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(result.getTotalElements())
				.resultsPerPage(result.getSize())
				.totalPages(result.getTotalPages())
				.numberOfElements(result.getNumberOfElements())
				.page(result.getNumber())
				.successMessage("Resultados Obtidos")
				.data(result.getContent())
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) {
		Optional<User> result = repository.findById(id);
		result.get().setPassword(null);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Resultados Obtidos")
				.data(result)
				.build();
		
		return resultRequest;
	}
	
	@Transactional
	public ReturnRequest insert(UserDTO dto) {
		User entity = this.modelMapper.map(dto, User.class);
		
		String encoded = encryptHash.encode(entity.getPassword());
		Dump.dd(encoded, "encoded");
		
		entity.setPassword(encryptHash.getHash(entity.getPassword()));
		
		User added = repository.save(entity);
		
		PermissionDTO permission = PermissionDTO.builder()
				.idUser(added.getId())
				.idGroup(2L)
				.build();
		
		insertPermission(permission);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1L)
				.successMessage("Usuário cadastrado com sucesso")
				.data(added)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest update(Long id, UserDTO dto) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		User entity = this.modelMapper.map(dto, User.class);
		
		entity.setPassword(encryptHash.getHash(entity.getPassword()));
		entity.setId(id);

		User updated = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Usuário atualizado com sucesso")
				.data(updated)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		repository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Usuário excluído com sucesso")
				.build();
		
		return resultRequest;
	}
	
	@Transactional
	public ReturnRequest insertPermission(PermissionDTO dto) {
		if (!repository.existsById(dto.getIdUser())) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		verifyPermissionActivated(dto);
		
		repository.insertPermission(dto.getIdUser(), dto.getIdGroup());
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Permissão cadastrada com sucesso")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	@Transactional
	public ReturnRequest deletePermission(Long idUser, Long idGroup) {
		if (!repository.existsById(idUser)) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		repository.deletePermission(idUser, idGroup);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Permissão excluída com sucesso")
				.build();
		
		return resultRequest;
	}
	
	public String findUserPassword(Long idConsultor) {
		List<User> passwords = repository.findUserPassword(idConsultor);
		
		if (passwords == null) {
			throw new RunTimeException("Usuário não pode autenticar.");
		}
		
		return passwords.get(0).getPassword();
	}
	
	private void verifyPermissionActivated(PermissionDTO dto) {
		Optional<User> result = repository.findById(dto.getIdUser());
		List<Group> groups = result.get().getGroups();
		
		if (groups != null) {
			for (Group group: groups) {
				if (result.get().getId() == dto.getIdUser() && group.getId() == dto.getIdGroup()) {
					throw new RunTimeException("Permissão já foi atribuida para este usuário.");
				}
			}
		}
	}
	
	@Transactional
	public ReturnRequest changePasswordUser(Long id, ChangePasswordDTO dto) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		String passwordHash = findUserPassword(id);
		verifyPasswordValid(dto.getOldPassword(), passwordHash);
		
		String passwordHashToSet = encryptHash.getHash(dto.getPassword());
		repository.changePasswordUser(id, passwordHashToSet);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Senha alterada com sucesso")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public boolean verifyPasswordValid(String password, String passwordHash) {
		boolean result = encryptHash.compareValueHash(password, passwordHash);
		
		if(!result) {
			throw new RunTimeException("Senha atual inválida.");
		}
		
		return result;
	}
}
