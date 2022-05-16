package com.checkWeather.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "Clima")
public class Clima {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer identificador;
	private double temperatura;
	private double sensacion;
	private double minima;
	private double maxima;
	private Integer presion;
	private Integer humedad;
	private Date fechaCreacion;
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	@JoinColumn(name = "id_ciudad")
	private Ciudad ciudad;
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipo")
	private Tipo tipo;
	
	
	public Integer getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public double getSensacion() {
		return sensacion;
	}
	public void setSensacion(double sensacion) {
		this.sensacion = sensacion;
	}
	public double getMinima() {
		return minima;
	}
	public void setMinima(double minima) {
		this.minima = minima;
	}
	public double getMaxima() {
		return maxima;
	}
	public void setMaxima(double maxima) {
		this.maxima = maxima;
	}
	public Integer getPresion() {
		return presion;
	}
	public void setPresion(Integer presion) {
		this.presion = presion;
	}
	public Integer getHumedad() {
		return humedad;
	}
	public void setHumedad(Integer humedad) {
		this.humedad = humedad;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
