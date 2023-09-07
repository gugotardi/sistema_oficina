package com.sistema.oficina.ordem_servico.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.oficina.ordem_servico.entities.Clientes;
import com.sistema.oficina.ordem_servico.entities.dto.ClienteDTO;
import com.sistema.oficina.ordem_servico.entities.dto.ClienteNewDTO;
import com.sistema.oficina.ordem_servico.service.ClienteService;

/*A annotation @RestController no Spring MVC nada mais é do que uma combinação das annotations @Controller e @ResponseBody
  No Framework Spring, um Controller é uma classe responsável pela preparação de um modelo de Map com dados a serem exibidos pela view e 
 * pela escolha da view correta. Basicamente ele é o responsável por controlar as requisições indicando quem deve 
 * receber as requisições para quem deve responde-las. Também pode mandar diretamente no fluxo do response usando a 
 * anotação @ResponseBody e concluir a solicitação.*/

/* Request Mapping: A anotação que indica que o controlador,que nesse caso é a classe Cliente receberá requisições feitas à URL
 * o @RequestMapping é genérico e vale para quantos métodos HTTP você quiser, enquanto o @GetMapping só 
 * vale para requisições GET, @PostMapping só para requisições POST e assim em diante  */

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Clientes> find(@PathVariable Integer id){
		
		Clientes obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Clientes obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();	 
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Clientes obj = clienteService.fromDTO(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Clientes> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
			
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<Clientes> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
			
	}
	
	
	
	
	
 
}
