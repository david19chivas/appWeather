package com.checkWeather.dto;

import com.checkWeather.model.Clima;

public class ClimaResponseDTO extends ResponseServerDTO {

	private static final long serialVersionUID = 1L;
	private Clima clima;

	public ClimaResponseDTO(int codeResponse, Clima clima) {
		super(codeResponse);
		this.clima = clima;
	}

	public ClimaResponseDTO(int codeResponse, String errorMessage) {
		super(errorMessage, codeResponse);
	}

	public Clima getClima() {
		return clima;
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

}
