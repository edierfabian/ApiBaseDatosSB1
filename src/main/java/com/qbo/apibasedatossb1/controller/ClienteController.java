package com.qbo.apibasedatossb1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.apibasedatossb1.exception.ResourceNotFoundException;
import com.qbo.apibasedatossb1.model.Cliente;
import com.qbo.apibasedatossb1.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

	
	@Autowired
	protected ClienteService clienteService;
	
	
	@GetMapping("/pageable")
	public ResponseEntity<?> searchByName(@RequestParam String nombre, Pageable pageable){
		
		Page<Cliente> clientes=clienteService.searchByName(nombre, pageable);
		if(clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	
	@GetMapping("")
	public  ResponseEntity<List<Cliente>> getAll(){
		
		List<Cliente> clientes=new ArrayList<Cliente>();
		clienteService.findAll().forEach(clientes::add);
		if(clientes.isEmpty()) {
			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable("id") long id) throws ResourceNotFoundException{
		Cliente cliente=clienteService.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException("No found State with id= " +id));
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}
	
	@GetMapping("/dni/{dni}")
	public ResponseEntity<Cliente> searchByDni(@PathVariable("dni")String dni) throws ResourceNotFoundException{
		
		Cliente cliente= clienteService.searchByDni(dni)
						.orElseThrow(()-> new ResourceNotFoundException("Not Found CLiente with DNI = "+dni));
		
		return new ResponseEntity<>(cliente,HttpStatus.OK);
		
	}
	
	@PostMapping("")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
		return new ResponseEntity<>(clienteService.save(cliente),HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable ("id")long id,
			@RequestBody Cliente cliente) throws ResourceNotFoundException{
		
		Cliente _cliente=clienteService.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException("No found State with id= " +id));
		_cliente.setNomcliente(cliente.getNomcliente());
		_cliente.setApecliente(cliente.getApecliente());
		_cliente.setDnicliente(cliente.getDnicliente());
		
		return new ResponseEntity<>(clienteService.save(_cliente),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") long id ) throws ResourceNotFoundException{
		
		clienteService.findById(id)
		.orElseThrow(()->
		new ResourceNotFoundException("Not found CLient with id = " +id));
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.deleteById(id));
	}
	
}
