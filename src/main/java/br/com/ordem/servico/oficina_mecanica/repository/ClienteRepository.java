package br.com.ordem.servico.oficina_mecanica.repository;

import br.com.ordem.servico.oficina_mecanica.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Transactional
    Cliente findByEmail(String email);
}
