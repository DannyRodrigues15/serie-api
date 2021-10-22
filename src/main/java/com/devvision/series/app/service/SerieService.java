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
import com.devvision.series.app.dto.SerieDTO;
import com.devvision.series.app.interfaces.service.IService;
import com.devvision.series.app.model.Serie;
import com.devvision.series.app.repository.SerieRepository;

@Service
public class SerieService implements IService<SerieDTO> {
	private ModelMapper modelMapper;
	
	public SerieService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	private SerieRepository repository;
	
	@Autowired
	private Status status;
	
	public ReturnRequest findAll(Pageable pageable) {
		Page<Serie> result = repository.findAll(pageable);

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
		Optional<Serie> result = repository.findById(id);
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
	
	public ReturnRequest insert(SerieDTO dto) {
		int seasonQuantity = dto.getSeasons().size();
		dto.setSeasons(null);
	
		Serie entity = this.modelMapper.map(dto, Serie.class);
		
		entity.setSeasons(seasonQuantity);
		
		Serie added = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1L)
				.successMessage("Serie cadastrada com sucesso")
				.data(added)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest update(Long id, SerieDTO dto) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Serie não existe na base de dados.");
		}
		
		int seasonQuantity = dto.getSeasons().size();
		dto.setSeasons(null);
		
		Serie entity = this.modelMapper.map(dto, Serie.class);
		
		entity.setId(id);
		entity.setSeasons(seasonQuantity);
		
		Serie updated = repository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1L)
				.successMessage("Serie atualizada com sucesso")
				.data(updated)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RunTimeException("Serie não existe na base de dados.");
		}
		
		repository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Serie excluído com sucesso")
				.build();
		
		return resultRequest;
	}
	
}
