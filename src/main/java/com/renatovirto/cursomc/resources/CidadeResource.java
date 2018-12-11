package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Cidade;
import com.renatovirto.cursomc.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cidade> listarCidades() {
		return cidadeService.listarTodasCidades();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cidade buscarCidade(@PathVariable Integer id) {
		return cidadeService.findCidade(id);
	}
}
