package com.devvision.series.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devvision.series.app.core.excepions.RunTimeException;
import com.devvision.series.app.core.utils.ReturnRequest;
import com.devvision.series.app.core.utils.Status;
import com.devvision.series.app.dto.MarkerDTO;
import com.devvision.series.app.interfaces.service.IService;
import com.devvision.series.app.model.Marker;
import com.devvision.series.app.repository.MarkerRepository;

@Service
public class MarkerService implements IService<MarkerDTO> {
	private ModelMapper modelMapper;
	
	public MarkerService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	private MarkerRepository repository;
	
	@Autowired
	private Status status;
	
	public ReturnRequest findAll(Pageable pageable) {
		Page<Marker> result = repository.findAll(pageable);

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
		Optional<Marker> result = repository.findById(id);
		result.get();
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Resultados Obtidos")
				.data(result)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest insert(MarkerDTO dto) {

		Marker entity = this.modelMapper.map(dto, Marker.class);
		
		Marker added = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1L)
				.successMessage("Marcado com sucesso")
				.data(added)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest update(Long id, MarkerDTO dto) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Marcador não existe na base de dados.");
		}
		
		Marker entity = this.modelMapper.map(dto, Marker.class);
		
		entity.setId(id);
		
		Marker updated = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Marcador atualizado com sucesso")
				.data(updated)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Marcador não existe na base de dados.");
		}
		
		repository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Marcador excluído com sucesso")
				.build();
		
		return resultRequest;
	}
	
	@Transactional
	public ReturnRequest updateEpisode(Long idMarker, MarkerDTO dto) {
		if (!repository.existsById(idMarker)) {
			throw new RunTimeException("Marcador não existe na base de dados.");
		}
		
		repository.updateEpisode(idMarker, dto.getEpisode().getId());
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Marcador atualizado com sucesso")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
}
