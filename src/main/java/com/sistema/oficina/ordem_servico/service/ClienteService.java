package com.sistema.oficina.ordem_servico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.oficina.ordem_servico.entities.Cidade;
import com.sistema.oficina.ordem_servico.entities.Clientes;
import com.sistema.oficina.ordem_servico.entities.Endereco;
import com.sistema.oficina.ordem_servico.entities.dto.ClienteDTO;
import com.sistema.oficina.ordem_servico.entities.dto.ClienteNewDTO;
import com.sistema.oficina.ordem_servico.entities.enums.TipoCliente;
import com.sistema.oficina.ordem_servico.repositories.ClienteRepository;
import com.sistema.oficina.ordem_servico.repositories.EnderecoRepository;
import com.sistema.oficina.ordem_servico.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Clientes find(Integer id) {
		Optional<Clientes> obj = clienteRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Clientes.class.getName()));
	}
	
	@Transactional
	public Clientes insert(Clientes obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
		
	}
	
	public Clientes update(Clientes obj) {
		Clientes newObj = find(obj.getId());
		updateData(newObj, obj);
		
		return clienteRepository.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir porque há ordens de serviço relacionado ao cliente");
		}
	}
	
	public List<Clientes> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Clientes> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Clientes fromDTO(ClienteDTO objDto) {
		return new Clientes(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		
	}
	
	public Clientes fromDTO(ClienteNewDTO objDto) {
		
		Clientes cli = new Clientes(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
		
	}
	
	
	private void updateData(Clientes newObj, Clientes obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
