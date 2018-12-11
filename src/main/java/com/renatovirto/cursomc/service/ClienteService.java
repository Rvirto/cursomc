package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Cliente;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarTodosClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		return clientes;
	}
	
	public Cliente buscarPeloIdCliente(Integer id) {
		Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
		
		return clienteBuscado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Cliente.class.getName()));
	}
}
