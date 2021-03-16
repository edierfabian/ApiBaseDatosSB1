package com.qbo.apibasedatossb1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbo.apibasedatossb1.model.Empleado;
import com.qbo.apibasedatossb1.repository.EmpleadoRepository;



@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	public HashMap<String, String> registrarEmpleado(Empleado empleado){
		
		empleadoRepository.registrarEmpleado(empleado.getNombre(), empleado.getApellido());
		
		HashMap<String , String> respuesta=new HashMap<String,String>();
		respuesta.put("mensaje","Elemento registrado correctamente");
		return respuesta;
		
	}
	
	
}
