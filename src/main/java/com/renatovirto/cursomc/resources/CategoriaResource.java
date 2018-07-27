package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Categoria;
import com.renatovirto.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarID(@PathVariable Integer id) {

		Categoria categoria = categoriaService.buscarPeloId(id);

		return ResponseEntity.ok().body(categoria);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> buscar() {

		List<Categoria> categorias = categoriaService.buscar();

		return categorias;
	}

}
