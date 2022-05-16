package com.checkWeather.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkWeather.model.Ciudad;

@Repository
public interface CiudadDao extends JpaRepository<Ciudad, Integer> {
	
	Ciudad findByIdentificadorCiudad(long idCiudad); 
	
	Ciudad findByNombreIgnoreCase(String nombre);
	
	List<Ciudad> findByOrderByFechaCreacionDesc(Pageable pageable);

}
