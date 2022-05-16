package com.checkWeather.dto;

import java.io.Serializable;

public class HistoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	public String nombreCiudad;
	public long numeroConsultas;
	
	public HistoryDTO() {
		super();
	}

	public HistoryDTO(String nombreCiudad, int numeroConsultas) {
		super();
		this.nombreCiudad = nombreCiudad;
		this.numeroConsultas = numeroConsultas;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public long getNumeroConsultas() {
		return numeroConsultas;
	}

	public void setNumeroConsultas(long numeroConsultas) {
		this.numeroConsultas = numeroConsultas;
	}
	
}
