package com.renatovirto.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renatovirto.cursomc.domain.Categoria;
import com.renatovirto.cursomc.dto.CategoriaDTO;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.service.CategoriaService;



@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarID(@PathVariable Integer id) throws ObjectNotFoundException {

		Categoria categoria = categoriaService.buscarPeloId(id);

		return ResponseEntity.ok().body(categoria);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CategoriaDTO> buscar() {

		List<Categoria> categorias = categoriaService.buscar();
		
		List<CategoriaDTO> categoriaDTO = categorias.stream()
				.map(objeto -> new CategoriaDTO(objeto))
					.collect(Collectors.toList());

		return categoriaDTO;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirCategoria(@Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.inserirCategoria(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
		categoriaService.atualizarCategoria(categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
		categoriaService.removerCategoria(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/pagina",method = RequestMethod.GET)
	public Page<CategoriaDTO> buscarPorPagina(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linhasPorPagina,
			@RequestParam(value="orderby", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {

		Page<Categoria> categorias = categoriaService
				.encontrarPagina(page, linhasPorPagina, orderBy, direction);
		
		Page<CategoriaDTO> categoriaDTO = categorias.map(objeto -> new CategoriaDTO(objeto));

		return categoriaDTO;
	}
}
