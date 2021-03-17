package com.qbo.apibasedatossb1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qbo.apibasedatossb1.model.Cliente;
import com.qbo.apibasedatossb1.repository.ClienteRepository;


@Service
public class ClienteService implements BaseService<Cliente>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Page<Cliente> searchByName(String nombre, Pageable pageable){
		return clienteRepository.searchByNameQueryNative(nombre, pageable);
		
	}
	
	public Optional<Cliente> searchByDni(String dniCLiente){
		Optional<Cliente> entityOptional=clienteRepository
			 				.searchByDniQueryNative(dniCLiente);
			if(entityOptional.isEmpty()) {
			
				return Optional.empty();
			}else {
			
				return entityOptional;
	       	}
		
	}
	
	@Override
	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		
		Optional<Cliente> entityOptional=clienteRepository.findById(id);
		if(entityOptional.isEmpty()) {
			return Optional.empty();
		}else {
			 return entityOptional;
			
		}
	
	}
		
	@Override
	public Cliente save(Cliente entity) {
		return clienteRepository.save(entity);
	}

	@Override
	public HashMap<String, String> deleteById(Long id) {
		HashMap<String, String> respuesta=new HashMap<String,String>();
		clienteRepository.deleteById(id);
		respuesta.put("mensaje", "Elemento eliminado correctamente");
		return respuesta;
	}

}
