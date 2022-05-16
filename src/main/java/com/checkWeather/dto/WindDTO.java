package com.checkWeather.dto;

import java.io.Serializable;

public class WindDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String speed;
	private String deg;
	private String gust;
	
	
	public WindDTO() {
		super();
	}


	public String getSpeed() {
		return speed;
	}


	public void setSpeed(String speed) {
		this.speed = speed;
	}


	public String getDeg() {
		return deg;
	}


	public void setDeg(String deg) {
		this.deg = deg;
	}


	public String getGust() {
		return gust;
	}


	public void setGust(String gust) {
		this.gust = gust;
	}
	
	
}
