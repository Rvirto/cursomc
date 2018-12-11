package com.renatovirto.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.cursomc.domain.Pagamento;
import com.renatovirto.cursomc.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoResource {

	@Autowired
	private PagamentoService pagamentoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Pagamento> buscarTodos() {
		return pagamentoService.listarTodosPagamentos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Pagamento buscarPeloID(@PathVariable Integer id) {
		return pagamentoService.buscarPagamentoPeloId(id);
	}
}
