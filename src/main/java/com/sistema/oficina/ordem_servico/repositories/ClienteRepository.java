package com.sistema.oficina.ordem_servico.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.oficina.ordem_servico.entities.Clientes;

@Repository
public interface ClienteRepository extends JpaRepository<Clientes, Integer> {
	
	@Transactional(readOnly=true)
	Clientes findByEmail(String email);

}
