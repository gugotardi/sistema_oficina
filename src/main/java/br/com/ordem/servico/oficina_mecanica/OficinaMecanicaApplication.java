package br.com.ordem.servico.oficina_mecanica;

import br.com.ordem.servico.oficina_mecanica.domain.Cidade;
import br.com.ordem.servico.oficina_mecanica.domain.Estado;
import br.com.ordem.servico.oficina_mecanica.repository.CidadeRepository;
import br.com.ordem.servico.oficina_mecanica.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OficinaMecanicaApplication implements CommandLineRunner {
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(OficinaMecanicaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");

		Cidade c1 = new Cidade(null,"Belo Horizonte",est2);
		Cidade c2 = new Cidade(null,"São Paulo",est1);
		Cidade c3 = new Cidade(null,"Botucatu",est1);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

	}


}
