package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Categoria;
import com.renatovirto.cursomc.exceptions.DataIntegrityViolation;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscarPeloId(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> objeto = categoriaRepository.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> buscar() {
		List<Categoria> objeto = categoriaRepository.findAll();
		return objeto;
	}
	
	public Categoria inserirCategoria(Categoria categoria) {
		categoria.setId(null);
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		return categoriaSalva;
	}

	public Categoria atualizarCategoria(Categoria categoria) {
		buscarPeloId(categoria.getId());
		Categoria categoriaAtualizada = categoriaRepository.save(categoria);
		return categoriaAtualizada;
	}

	public void removerCategoria(Integer id) {
		buscarPeloId(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolation("Não foi possível excluir esta Categoria, pois ela possui produtos!");
		}
		
	}
	
	public Page<Categoria> encontrarPagina(Integer page, Integer linhasPorPagina, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direction), orderBy);
		
		return categoriaRepository.findAll(pageRequest);
	}
}
