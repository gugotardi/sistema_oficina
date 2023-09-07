package com.sistema.oficina.ordem_servico.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.oficina.ordem_servico.controllers.exception.FieldMessage;
import com.sistema.oficina.ordem_servico.entities.Clientes;
import com.sistema.oficina.ordem_servico.entities.dto.ClienteNewDTO;
import com.sistema.oficina.ordem_servico.entities.enums.TipoCliente;
import com.sistema.oficina.ordem_servico.repositories.ClienteRepository;
import com.sistema.oficina.ordem_servico.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURICADICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURICADICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Clientes aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux !=null) {
			list.add(new FieldMessage("email","Email já existente"));
		}
		
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
			       .addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
