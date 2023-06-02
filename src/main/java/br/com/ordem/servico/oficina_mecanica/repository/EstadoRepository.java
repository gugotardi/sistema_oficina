package br.com.ordem.servico.oficina_mecanica.repository;

import br.com.ordem.servico.oficina_mecanica.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}