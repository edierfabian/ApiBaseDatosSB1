package com.qbo.apibasedatossb1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuarios {
	
	@Id
	private Long idusuario;
	private String nomusuario;
	private String token;
	public Long getIdusuario() {
		return idusuario;
	}
	public String getNomusuario() {
		return nomusuario;
	}
	public String getToken() {
		return token;
	}
	public Usuarios(Long idusuario, String nomusuario, String token) {
		super();
		this.idusuario = idusuario;
		this.nomusuario = nomusuario;
		this.token = token;
	}
	public Usuarios() {
		super();
	}

	
	
	
	

}
