package com.renatovirto.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirPedido(@Valid @RequestBody Pedido pedido) {
		Pedido pedidoSalvo = pedidoService.inserirPedido(pedido);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pedidoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
