package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Cidade;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> listarTodasCidades() {
		List<Cidade> cidades = cidadeRepository.findAll();
		
		return cidades;
	}
	
	public Cidade findCidade(Integer id) {
		Optional<Cidade> cidadeBuscada = cidadeRepository.findById(id);
		
		return cidadeBuscada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Classe: " + Cidade.class.getName()));
	}
}
