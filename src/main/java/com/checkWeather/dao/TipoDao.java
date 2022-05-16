package com.checkWeather.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkWeather.model.Tipo;

@Repository
public interface TipoDao extends JpaRepository<Tipo, Integer> {
	
	Optional<Tipo> findByIdentificadorClima(Integer identificador);

}
