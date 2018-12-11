package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Endereco;
import com.renatovirto.cursomc.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Endereco> listarTodos() {
		return enderecoService.listarTodosEnderecos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Endereco buscarEndereco(@PathVariable Integer id) {
		return enderecoService.buscarEnderecoPeloId(id);
	}
}
