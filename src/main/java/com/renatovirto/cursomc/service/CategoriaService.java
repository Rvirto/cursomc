package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Categoria;
import com.renatovirto.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscarPeloId(Integer id) {
		Optional<Categoria> objeto = categoriaRepository.findById(id);
		return objeto.orElse(null);
	}
	
	public List<Categoria> buscar() {
		List<Categoria> objeto = categoriaRepository.findAll();
		return objeto;
	}
	
	public void salvar(Categoria cat) {
		categoriaRepository.save(cat);
	}
}
