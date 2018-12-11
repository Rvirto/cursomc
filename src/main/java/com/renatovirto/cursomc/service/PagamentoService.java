package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Pagamento;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public List<Pagamento> listarTodosPagamentos() {
		List<Pagamento> pagamentos = pagamentoRepository.findAll();
		
		return pagamentos;
	}
	
	public Pagamento buscarPagamentoPeloId(Integer id) {
		Optional<Pagamento> pagamentoBuscado = pagamentoRepository.findById(id);
		
		return pagamentoBuscado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Pagamento.class.getName()));
	}
}
