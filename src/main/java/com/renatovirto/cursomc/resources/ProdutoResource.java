package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Produto;
import com.renatovirto.cursomc.dto.ProdutoDTO;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.resources.utils.URL;
import com.renatovirto.cursomc.service.ProdutoService;


@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarProdutoID(@PathVariable Integer id) throws ObjectNotFoundException {
		Produto produtoBuscado = produtoService.buscarProdutoPeloId(id);
		
		return ResponseEntity.ok().body(produtoBuscado);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> buscarProdutos() {
		return produtoService.buscarTodosProdutos();
	}
	
	@RequestMapping(value = "/pagina",method = RequestMethod.GET)
	public Page<ProdutoDTO> buscarPorPagina(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linhasPorPagina,
			@RequestParam(value="orderby", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {

		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded =  URL.decodeParam(nome);
		Page<Produto> produtos = produtoService
				.search(nomeDecoded, ids, page, linhasPorPagina, orderBy, direction);
		
		Page<ProdutoDTO> produtoDTO = produtos.map(objeto -> new ProdutoDTO(objeto));

		return produtoDTO;
	}
}
