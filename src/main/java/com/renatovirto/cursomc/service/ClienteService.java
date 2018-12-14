package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Cliente;
import com.renatovirto.cursomc.exceptions.DataIntegrityViolation;
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
	
	public Cliente inserirCliente(Cliente cliente) {
		cliente.setId(null);
		Cliente ClienteSalvo = clienteRepository.save(cliente);
		
		return ClienteSalvo;
	}

	public Cliente atualizarCliente(Cliente cliente) {
		buscarPeloIdCliente(cliente.getId());
		Cliente ClienteAtualizada = clienteRepository.save(cliente);
		return ClienteAtualizada;
	}

	public void removerCliente(Integer id) {
		buscarPeloIdCliente(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolation("Não foi possível excluir este Cliente, pois ele possui Pedidos!");
		}
		
	}
	
	public Page<Cliente> encontrarPagina(Integer page, Integer linhasPorPagina, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}
}
