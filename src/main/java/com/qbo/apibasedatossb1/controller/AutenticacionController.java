package com.qbo.apibasedatossb1.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.apibasedatossb1.model.Empleado;
import com.qbo.apibasedatossb1.model.Usuarios;
import com.qbo.apibasedatossb1.service.EmpleadoService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/v1/auteticacion")
public class AutenticacionController {

	
	@Autowired
	EmpleadoService empleadoService;
	
	
	@PostMapping("")
	public Usuarios login(@RequestParam("usuario")String usuario,
						 @RequestParam("password")String password){
		
		Empleado empleado=empleadoService
				.autenticarEmpleado(usuario, password);
		if(empleado!=null) {
			String token=generarToken(usuario);
			Usuarios objusuario=new Usuarios(empleado.getIdempleado(),
					empleado.getNombre(),
					token);
			return objusuario;
		}
		
		return null;
	}
	
	private String generarToken(String usuario) {
		String clavesecreta="@QBO2021";
		List<GrantedAuthority> lstautorizacion=
				AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		//LOS ROLES DE LOS USUARIOS DEBEN CARGARSE DE MANERA DINAMICA(BD)
		String token=Jwts
				.builder()
				.setId("@qboJWT")
				.setSubject(usuario)
				.claim("authorities", 
						lstautorizacion
						.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+600000))
				.signWith(SignatureAlgorithm.HS512, clavesecreta.getBytes())
				.compact();
		return "Bearer "+token;
		
	}
}
