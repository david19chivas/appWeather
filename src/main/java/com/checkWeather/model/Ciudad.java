package com.checkWeather.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ciudad")
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private Long identificadorCiudad;
	private String nombre;
	private double longitud;
	private double latitud;
	private Date fechaCreacion;
	@JsonIgnore
	@OneToMany(mappedBy = "ciudad", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Clima> listClima;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public List<Clima> getListClima() {
		return listClima;
	}

	public void setListClima(List<Clima> listClima) {
		this.listClima = listClima;
	}

	public Long getIdentificadorCiudad() {
		return identificadorCiudad;
	}

	public void setIdentificadorCiudad(Long identificadorCiudad) {
		this.identificadorCiudad = identificadorCiudad;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
