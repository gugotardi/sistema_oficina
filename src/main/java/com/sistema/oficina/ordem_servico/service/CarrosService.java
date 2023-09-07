package com.sistema.oficina.ordem_servico.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.oficina.ordem_servico.entities.Carros;
import com.sistema.oficina.ordem_servico.repositories.CarroRepository;

@Service
public class CarrosService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	public Carros find(Long idCarro) {
		Optional<Carros> obj = carroRepository.findById(idCarro);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id:" + idCarro, Carros.class.getName()));	
	}
	
	public List<Carros> findAll(){
		return carroRepository.findAll();
	}
	
	

}
