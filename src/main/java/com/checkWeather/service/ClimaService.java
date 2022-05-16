package com.checkWeather.service;

import com.checkWeather.dto.ResponseServerDTO;

public interface ClimaService {
	
	public ResponseServerDTO getWeather(String nameCity);
	
	public ResponseServerDTO getHistoryService();

}
