package com.sistema.oficina.ordem_servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.oficina.ordem_servico.entities.Carros;

@Repository
public interface CarroRepository extends JpaRepository<Carros, Long> {

}
