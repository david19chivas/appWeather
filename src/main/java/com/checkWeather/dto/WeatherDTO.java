package com.checkWeather.dto;

import java.io.Serializable;

public class WeatherDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String main;
	private String description;
	private String icon;
	
	
	public WeatherDTO() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMain() {
		return main;
	}


	public void setMain(String main) {
		this.main = main;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	

}
