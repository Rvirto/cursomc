package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Estado;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listarTodosEstados() {
		List<Estado> estados = estadoRepository.findAll();
		
		return estados;
	}
	
	public Estado findEstado(Integer id) {
		Optional<Estado> estadoBuscado = estadoRepository.findById(id);
		
		return estadoBuscado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Estado.class.getName()));
	}
}
