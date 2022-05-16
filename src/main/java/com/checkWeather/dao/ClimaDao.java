package com.checkWeather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkWeather.model.Ciudad;
import com.checkWeather.model.Clima;

@Repository
public interface ClimaDao extends JpaRepository<Clima, Integer> {

	List<Clima> findByCiudadOrderByFechaCreacionDesc(Ciudad ciudad);
	
	long countByCiudad(Ciudad ciudad);
	
}
