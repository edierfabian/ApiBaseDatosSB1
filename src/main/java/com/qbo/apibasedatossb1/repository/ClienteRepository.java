package com.qbo.apibasedatossb1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.qbo.apibasedatossb1.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query(
			value="SELECT * FROM cliente WHERE cliente.dnicliente=:dnicliente",
			nativeQuery = true
			)
	Optional<Cliente> searchByDniQueryNative(@Param("dnicliente")String dniCliente);
	

}
