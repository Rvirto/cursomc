package com.renatovirto.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.renatovirto.cursomc.domain.Categoria;
import com.renatovirto.cursomc.domain.Cidade;
import com.renatovirto.cursomc.domain.Cliente;
import com.renatovirto.cursomc.domain.Endereco;
import com.renatovirto.cursomc.domain.Estado;
import com.renatovirto.cursomc.domain.ItemPedido;
import com.renatovirto.cursomc.domain.Pagamento;
import com.renatovirto.cursomc.domain.PagamentoComBoleto;
import com.renatovirto.cursomc.domain.PagamentoComCartao;
import com.renatovirto.cursomc.domain.Pedido;
import com.renatovirto.cursomc.domain.Produto;
import com.renatovirto.cursomc.domain.enums.EstadoPagamento;
import com.renatovirto.cursomc.domain.enums.TipoCliente;
import com.renatovirto.cursomc.repository.CategoriaRepository;
import com.renatovirto.cursomc.repository.CidadeRepository;
import com.renatovirto.cursomc.repository.ClienteRepository;
import com.renatovirto.cursomc.repository.EnderecoRepository;
import com.renatovirto.cursomc.repository.EstadoRepository;
import com.renatovirto.cursomc.repository.ItemPedidoRepository;
import com.renatovirto.cursomc.repository.PagamentoRepository;
import com.renatovirto.cursomc.repository.PedidoRepository;
import com.renatovirto.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaService;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Escritório");
		Categoria cat2 = new Categoria(null, "Informática");
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaService.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cli1 = new Cliente(null, "Matheus Cavalcanti", "matheuschupetinha@gmail.com", "158.102.852-52", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("996457525", "997142563"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Casa", "Leny", "19807-125", cli1, cidade1);
		
		Endereco end2 = new Endereco(null, "Avenida Kobol", "150", "Casa", "Kobolândia", "19897-325", cli1, cidade2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2018 10:32"), cli1, end1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2018 19:35"), cli1, end2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, 
				pedido2, sdf.parse("20/10/2017 00:00"), null);
		
		pedido2.setPagamento(pagamento2);
		
		cli1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		
		ItemPedido item1 = new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(pedido2, p2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(item1, item2));
		
		pedido2.getItens().addAll(Arrays.asList(item3));
		
		p1.getItens().addAll(Arrays.asList(item1));
		p2.getItens().addAll(Arrays.asList(item3));
		p3.getItens().addAll(Arrays.asList(item2));
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
	}

}
