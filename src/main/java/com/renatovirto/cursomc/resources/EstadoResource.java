package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Estado;
import com.renatovirto.cursomc.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Estado> listarTodas() {
		return estadoService.listarTodosEstados();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Estado buscarEstado(@PathVariable Integer id) {
		return estadoService.findEstado(id);
	}
}
