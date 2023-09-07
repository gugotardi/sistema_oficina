package com.sistema.oficina.ordem_servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.oficina.ordem_servico.entities.Carros;

public interface CarroRepository extends JpaRepository<Carros, Long> {

}
