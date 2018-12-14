package com.renatovirto.cursomc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.ItemPedido;
import com.renatovirto.cursomc.domain.PagamentoComBoleto;
import com.renatovirto.cursomc.domain.Pedido;
import com.renatovirto.cursomc.domain.enums.EstadoPagamento;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.ItemPedidoRepository;
import com.renatovirto.cursomc.repository.PagamentoRepository;
import com.renatovirto.cursomc.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public List<Pedido> listarTodosPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}
	
	public Pedido buscarPedidoPeloID(Integer id) {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(id);
		
		return pedidoBuscado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Pedido.class.getName()));
	}

	public Pedido inserirPedido(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pag = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pag, pedido.getInstante());
		}
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedidoSalvo.getPagamento());
		
		for(ItemPedido ip: pedidoSalvo.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscarProdutoPeloId(ip.getProduto().getId()).getPreco());
			ip.setPedido(pedidoSalvo);
		}
		itemPedidoRepository.saveAll(pedidoSalvo.getItens());
		return pedidoSalvo;
	}
}
