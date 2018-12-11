package com.renatovirto.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.ItemPedido;
import com.renatovirto.cursomc.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> buscarTodosItens() {
		List<ItemPedido> itens = itemPedidoRepository.findAll();
		return itens;
	}
	
	public void buscarITemPedidoPeloID(Integer id) {
		
	}
}
