package com.checkWeather.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checkWeather.dto.ResponseServerDTO;
import com.checkWeather.service.ClimaService;

@RestController
@RequestMapping("/weathers")
public class ClimaController {
	
	@Autowired
	private ClimaService climaService;
	
	private static final Logger log = LogManager.getLogger(ClimaController.class);
	
	
	@GetMapping(path = "/getWeather")
	public ResponseEntity<ResponseServerDTO> getWeather(@RequestParam (value = "nameCity") String nameCity) {
		log.info("---------------- getting weather ------------");
		ResponseServerDTO response = climaService.getWeather(nameCity);
		switch (response.getCodeResponse()) {
		case 500:
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		case 406:
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		case 404:	
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		case 304:
			return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
		case 206:
			return new ResponseEntity<>(response, HttpStatus.PARTIAL_CONTENT);
		default:
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
			}
	
	
	@GetMapping(path = "/getHistory")
	public ResponseEntity<ResponseServerDTO> getHistory(){
		log.info("---------------- getting history --------------");
		ResponseServerDTO response = climaService.getHistoryService();
		switch (response.getCodeResponse()) {
		case 500:
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		case 404:	
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		default:
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

}
