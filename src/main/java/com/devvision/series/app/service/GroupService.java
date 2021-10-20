package com.devvision.series.app.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devvision.series.app.core.excepions.RunTimeException;
import com.devvision.series.app.core.utils.ReturnRequest;
import com.devvision.series.app.core.utils.Status;
import com.devvision.series.app.dto.GroupDTO;
import com.devvision.series.app.interfaces.service.IService;
import com.devvision.series.app.model.Group;
import com.devvision.series.app.repository.GroupRepository;

@Service
public class GroupService implements IService<GroupDTO> {
	private ModelMapper modelMapper;
	
	public GroupService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	private GroupRepository repository;
	
	@Autowired
	private Status status;

	public ReturnRequest findAll(Pageable pageable) {
		Page<Group> result = repository.findAll(pageable);

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
		Optional<Group> result = repository.findById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Resultados Obtidos")
				.data(result)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest insert(GroupDTO dto) {
		Group entity = this.modelMapper.map(dto, Group.class);
		
		Group added = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1L)
				.successMessage("Grupo cadastrado com sucesso")
				.data(added)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest update(Long id, GroupDTO dto) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Grupo não existe na base de dados.");
		}
		
		Group entity = this.modelMapper.map(dto, Group.class);
		entity.setId(id);

		Group updated = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Grupo atualizado com sucesso")
				.data(updated)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Grupo não existe na base de dados.");
		}
		
		repository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Grupo excluído com sucesso")
				.build();
		
		return resultRequest;
	}
}
