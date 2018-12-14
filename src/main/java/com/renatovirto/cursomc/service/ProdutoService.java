package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Categoria;
import com.renatovirto.cursomc.domain.Produto;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.CategoriaRepository;
import com.renatovirto.cursomc.repository.ProdutoRepository;



@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto buscarProdutoPeloId(Integer id) throws ObjectNotFoundException {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Produto.class.getName()));
	}
	
	public List<Produto> buscarTodosProdutos() {
		List<Produto> produtos = produtoRepository.findAll();
		
		return produtos;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linhasPorPagina, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}
}
