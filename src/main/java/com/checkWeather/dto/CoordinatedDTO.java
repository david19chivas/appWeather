package com.checkWeather.dto;

import java.io.Serializable;

public class CoordinatedDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private double lon;
	private double lat;
	
	public CoordinatedDTO () {
		super();
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	
	
	
	
}
