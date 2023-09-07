package com.sistema.oficina.ordem_servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.oficina.ordem_servico.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
