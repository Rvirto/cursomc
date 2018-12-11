package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Pedido;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listarTodosPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}
	
	public Pedido buscarPedidoPeloID(Integer id) {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(id);
		
		return pedidoBuscado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Pedido.class.getName()));
	}
}
