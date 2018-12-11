package com.renatovirto.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.Cliente;
import com.renatovirto.cursomc.domain.Endereco;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;
import com.renatovirto.cursomc.repository.EnderecoRepository;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> listarTodosEnderecos() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		
		return enderecos;
	}
	
	public Endereco buscarEnderecoPeloId(Integer id) {
		Optional<Endereco> enderecoBuscado = enderecoRepository.findById(id);
		
		return enderecoBuscado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Classe: " + Cliente.class.getName()));
	}
}
