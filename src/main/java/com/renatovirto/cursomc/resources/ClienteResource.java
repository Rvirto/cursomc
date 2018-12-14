package com.renatovirto.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renatovirto.cursomc.domain.Cliente;
import com.renatovirto.cursomc.dto.ClienteDTO;
import com.renatovirto.cursomc.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ClienteDTO> buscar() {

		List<Cliente> clientes = clienteService.listarTodosClientes();
		
		List<ClienteDTO> clientesDTO = clientes.stream()
				.map(objeto -> new ClienteDTO(objeto))
					.collect(Collectors.toList());

		return clientesDTO;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cliente buscarClientePeloID(@PathVariable Integer id) {
		return clienteService.buscarPeloIdCliente(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteService.inserirCliente(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteSalvo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
		clienteService.atualizarCliente(cliente);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
		clienteService.removerCliente(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/pagina",method = RequestMethod.GET)
	public Page<ClienteDTO> buscarPorPagina(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linhasPorPagina,
			@RequestParam(value="orderby", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {

		Page<Cliente> clientes = clienteService
				.encontrarPagina(page, linhasPorPagina, orderBy, direction);
		
		Page<ClienteDTO> clientesODT = clientes.map(objeto -> new ClienteDTO(objeto));

		return clientesODT;
	}
}
