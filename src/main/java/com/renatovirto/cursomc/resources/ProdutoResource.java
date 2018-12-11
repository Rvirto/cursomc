package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Produto;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
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
}
