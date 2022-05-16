package com.checkWeather.model;

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
@Table(name = "Tipo")
public class Tipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private Integer identificadorClima;
	private String nombre;
	private String descripcion;
	private String icono;
	@JsonIgnore
	@OneToMany(mappedBy = "tipo", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Clima> listClima;
	
	public Integer getIdentificadorClima() {
		return identificadorClima;
	}
	public void setIdentificadorClima(Integer identificadorClima) {
		this.identificadorClima = identificadorClima;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public List<Clima> getListClima() {
		return listClima;
	}
	public void setListClima(List<Clima> listClima) {
		this.listClima = listClima;
	}
	
}
