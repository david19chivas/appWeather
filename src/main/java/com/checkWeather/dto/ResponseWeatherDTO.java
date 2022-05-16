package com.checkWeather.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseWeatherDTO implements Serializable {

	private static final long serialVersionUID = -7926579049368296373L;
	private CoordinatedDTO coord;
	private List<WeatherDTO> weather;
	private String base;
	private MainDTO main;
	private String visibility;
	private WindDTO wind;
	private CloudsDTO clouds;
	private String dt;
	private SysDTO sys;
	private String timezone;
	private long id;
	private String name;
	private Integer cod;

	public ResponseWeatherDTO() {
		super();
	}
	

	public ResponseWeatherDTO(CoordinatedDTO coord, List<WeatherDTO> weather, String base, MainDTO main, String visibility, WindDTO wind,
			CloudsDTO clouds, String dt, SysDTO sys, String timezone, Integer id, String name, Integer cod) {
		super();
		this.coord = coord;
		this.weather = weather;
		this.base = base;
		this.main = main;
		this.visibility = visibility;
		this.wind = wind;
		this.clouds = clouds;
		this.dt = dt;
		this.sys = sys;
		this.timezone = timezone;
		this.id = id;
		this.name = name;
		this.cod = cod;
	}




	public CoordinatedDTO getCoord() {
		return coord;
	}

	public void setCoord(CoordinatedDTO coord) {
		this.coord = coord;
	}

	public List<WeatherDTO> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherDTO> weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public MainDTO getMain() {
		return main;
	}

	public void setMain(MainDTO main) {
		this.main = main;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public WindDTO getWind() {
		return wind;
	}

	public void setWind(WindDTO wind) {
		this.wind = wind;
	}

	public CloudsDTO getClouds() {
		return clouds;
	}

	public void setClouds(CloudsDTO clouds) {
		this.clouds = clouds;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}
	
	public SysDTO getSys() {
		return sys;
	}

	public void setSys(SysDTO sys) {
		this.sys = sys;
	}


	@Override
	public String toString() {
		return "Response [coord=" + coord + ", weather=" + weather + ", base=" + base + ", main=" + main
				+ ", visibility=" + visibility + ", wind=" + wind + ", clouds=" + clouds + ", dt=" + dt + ", timezone="
				+ timezone + ", id=" + id + ", name=" + name + ", cod=" + cod + "]";
	}

}
