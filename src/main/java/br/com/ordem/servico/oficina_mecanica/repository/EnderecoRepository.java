package br.com.ordem.servico.oficina_mecanica.repository;

import br.com.ordem.servico.oficina_mecanica.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
