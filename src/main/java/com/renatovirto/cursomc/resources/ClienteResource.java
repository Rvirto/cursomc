package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Cliente;
import com.renatovirto.cursomc.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listarTodos() {
		return clienteService.listarTodosClientes();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cliente buscarClientePeloID(@PathVariable Integer id) {
		return clienteService.buscarPeloIdCliente(id);
	}
}
