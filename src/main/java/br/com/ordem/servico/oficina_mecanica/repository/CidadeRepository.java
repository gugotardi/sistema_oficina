package br.com.ordem.servico.oficina_mecanica.repository;


import br.com.ordem.servico.oficina_mecanica.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
