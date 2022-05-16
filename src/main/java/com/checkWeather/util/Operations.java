package com.checkWeather.util;

import java.text.DecimalFormat;

public class Operations {
	
    private static final DecimalFormat df = new DecimalFormat("0.00");

	
	public static double convertCelsius(double tempKelvin) {
		double fk = 273.15;
		return Double.valueOf( df.format(tempKelvin - fk)); 
	}

}
