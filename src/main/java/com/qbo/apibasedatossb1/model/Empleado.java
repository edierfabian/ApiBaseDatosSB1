package com.qbo.apibasedatossb1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
//@Table(name= "empleado")
public class Empleado {
	
	@Id
	private Long idEmpleado;
	private String nombre;
	private String apellido;
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	
	
	
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public Empleado(Long idEmpleado, String nombre, String apellido) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
	}



	public Empleado(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}



	public Empleado() {
		super();
	}
	
	
	
	
}
