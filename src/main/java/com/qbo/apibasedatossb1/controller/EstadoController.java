package com.qbo.apibasedatossb1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.apibasedatossb1.exception.ResourceNotFoundException;
import com.qbo.apibasedatossb1.model.Estado;
import com.qbo.apibasedatossb1.service.EstadoService;

@RestController
@RequestMapping("/api/v1/estado")
public class EstadoController {
	
	@Autowired
	protected EstadoService estadoService;
	
	@GetMapping("")
	public ResponseEntity<List<Estado>> getAll(){
		
		List<Estado> entities=new ArrayList<Estado>();
		estadoService.findAll().forEach(entities::add);
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }else {
			 
			 return new ResponseEntity<>(entities,HttpStatus.OK);
			 
		 }
	}
		
		
	@GetMapping("/{id}")
	public ResponseEntity<Estado> getStateById(@PathVariable("id") long id) throws ResourceNotFoundException{
		
	Estado entity =estadoService.findById(id)
						.orElseThrow(()-> new ResourceNotFoundException("Not Found State with Id = "+id));
	
		return new ResponseEntity<>(entity, HttpStatus.OK);
			
	}
	
	@PostMapping("")
	public ResponseEntity<Estado> createState(@RequestBody Estado estado){
		
		return new ResponseEntity<>(estadoService.save(new Estado(estado.getDescestado())),
				HttpStatus.CREATED);
				
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> updateState(@PathVariable("id") long id,
			@RequestBody Estado estado) throws ResourceNotFoundException{
		
		Estado _estado = estadoService.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException("Not Found State with Id = "+id));
		_estado.setDescestado(estado.getDescestado());
		
		return new ResponseEntity<>(estadoService.save(_estado),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteState(@PathVariable("id") long id) throws ResourceNotFoundException{
		
		estadoService.findById(id)
			.orElseThrow(()->
			new ResourceNotFoundException("Not Found State with Id = "+id));
		
		return ResponseEntity.status(HttpStatus.OK).body(estadoService.deleteById(id));
	} 
}
