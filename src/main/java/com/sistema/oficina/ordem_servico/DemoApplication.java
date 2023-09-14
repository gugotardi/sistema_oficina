package com.sistema.oficina.ordem_servico;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.oficina.ordem_servico.entities.Cidade;
import com.sistema.oficina.ordem_servico.entities.Clientes;
import com.sistema.oficina.ordem_servico.entities.Endereco;
import com.sistema.oficina.ordem_servico.entities.Estado;
import com.sistema.oficina.ordem_servico.entities.enums.TipoCliente;
import com.sistema.oficina.ordem_servico.repositories.CidadeRepository;
import com.sistema.oficina.ordem_servico.repositories.ClienteRepository;
import com.sistema.oficina.ordem_servico.repositories.EnderecoRepository;
import com.sistema.oficina.ordem_servico.repositories.EstadoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Lavras", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Clientes cli1 = new Clientes(null,"Gustavo Gotardi","gustavo@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2)); 
		
		
	}

}
