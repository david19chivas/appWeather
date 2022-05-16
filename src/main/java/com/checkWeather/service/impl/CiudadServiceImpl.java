package com.checkWeather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkWeather.dao.CiudadDao;
import com.checkWeather.model.Ciudad;

@Service
public class CiudadServiceImpl {
	
	@Autowired
	private CiudadDao ciudadDao;
	
	
	private Ciudad addCity(Ciudad city) {
		return ciudadDao.save(city);
	}
	
	

}
