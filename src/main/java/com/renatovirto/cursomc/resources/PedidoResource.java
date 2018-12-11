package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Pedido;
import com.renatovirto.cursomc.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Pedido> buscarTodos() {
		return pedidoService.listarTodosPedidos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Pedido buscarPeloId(@PathVariable Integer id) {
		return pedidoService.buscarPedidoPeloID(id);
	}
}
