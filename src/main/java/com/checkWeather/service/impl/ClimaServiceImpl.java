package com.checkWeather.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.checkWeather.dao.CiudadDao;
import com.checkWeather.dao.ClimaDao;
import com.checkWeather.dao.TipoDao;
import com.checkWeather.dto.ClimaResponseDTO;
import com.checkWeather.dto.CloudsDTO;
import com.checkWeather.dto.CoordinatedDTO;
import com.checkWeather.dto.HistoryDTO;
import com.checkWeather.dto.HistoryResponseDTO;
import com.checkWeather.dto.MainDTO;
import com.checkWeather.dto.ResponseServerDTO;
import com.checkWeather.dto.SysDTO;
import com.checkWeather.dto.WeatherDTO;
import com.checkWeather.dto.WindDTO;
import com.checkWeather.model.Ciudad;
import com.checkWeather.model.Clima;
import com.checkWeather.model.Tipo;
import com.checkWeather.service.ClimaService;
import com.checkWeather.util.Operations;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ClimaServiceImpl implements ClimaService {

	private static final Logger log = LogManager.getLogger(ClimaService.class);
	private OkHttpClient client;
	private Response response;
	private ResponseServerDTO responseServerDTO;

	@Value("${api.key}")
	private String API;

	@Autowired
	private ClimaDao climaDao;

	@Autowired
	private TipoDao tipoDao;

	@Autowired
	private CiudadDao ciudadDao;

	@Override
	public ResponseServerDTO getWeather(String nameCity) {
		Clima clima = null;
		if (null != nameCity && !nameCity.isEmpty()) {
			clima = new Clima();
			JSONObject bodyRequest = checkWeatherCity(nameCity);
			if (null != bodyRequest) {
				com.checkWeather.dto.ResponseWeatherDTO resp = this.buildResponse(bodyRequest);
				Tipo t = new Tipo();
				Optional<Tipo> tipo = tipoDao.findByIdentificadorClima(resp.getWeather().get(0).getId());
				if (tipo.isEmpty()) {
					t = new Tipo();
					t.setIdentificadorClima(resp.getWeather().get(0).getId());
					t.setNombre(resp.getWeather().get(0).getMain());
					t.setDescripcion(resp.getWeather().get(0).getDescription());
					t.setIcono(resp.getWeather().get(0).getIcon());
					tipoDao.save(t);
				} else {
					t = tipo.get();
				}

				Ciudad city = ciudadDao.findByIdentificadorCiudad(resp.getId());
				if (null == city) {
					city = new Ciudad();
					city.setNombre(resp.getName());
					city.setIdentificadorCiudad(resp.getId());
					city.setLatitud(resp.getCoord().getLat());
					city.setLongitud(resp.getCoord().getLon());
					city.setFechaCreacion(new Date());
					ciudadDao.save(city);
				}
				clima.setTemperatura(resp.getMain().getTemp());
				clima.setSensacion(resp.getMain().getFeels_like());
				clima.setPresion(resp.getMain().getPressure());
				clima.setMinima(resp.getMain().getTemp_min());
				clima.setMaxima(resp.getMain().getTemp_max());
				clima.setHumedad(resp.getMain().getHumidity());
				clima.setFechaCreacion(new Date());
				clima.setCiudad(city);
				clima.setTipo(t);
				climaDao.save(clima);
				responseServerDTO = new ClimaResponseDTO(HttpStatus.OK.value(), clima);
			} else {
				// Inicializando la cache
				Ciudad ciudadCache = ciudadDao.findByNombreIgnoreCase(nameCity);
				if (null != ciudadCache) {
					List<Clima> climas = climaDao.findByCiudadOrderByFechaCreacionDesc(ciudadCache);
					if (!climas.isEmpty()) {
						clima = climas.get(0);
						responseServerDTO = new ClimaResponseDTO(HttpStatus.PARTIAL_CONTENT.value(), clima);
					} else {
						responseServerDTO = new ClimaResponseDTO(HttpStatus.NOT_FOUND.value(),
								"No se encuentra el recurso disponible.");
					}
				} else {
					responseServerDTO = new ClimaResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(),
							"No se encuentra información para este dato: " + nameCity);
				}
			}
		} else {
			responseServerDTO = new ClimaResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"No se ha enviado una ciudad para consultar.");
		}
		return responseServerDTO;

	}

	public JSONObject checkWeatherCity(String nameCity) {
		try {
			responseServerDTO = new ResponseServerDTO();
			client = new OkHttpClient();
			Request request = new Request.Builder()
					.url("https://api.openweathermap.org/data/2.5/weather?q=" + nameCity + "&appid=" + API).build();
			response = client.newCall(request).execute();
			if (response.code() == HttpStatus.OK.value()) {
				return new JSONObject(response.body().string());
			} else {
				responseServerDTO = new ClimaResponseDTO(response.code(), response.body().string());
				return null;
			}
		} catch (IOException ex) {
			log.error(ex);
			return null;
		}
	}

	public JSONObject getCoord(JSONObject bodyResponse) throws JSONException {
		log.info("getting coord");
		return bodyResponse.getJSONObject("coord");
	}

	public CoordinatedDTO buildCoord(JSONObject coordObject) {
		CoordinatedDTO coord = new CoordinatedDTO();
		coord.setLon(Double.parseDouble(coordObject.get("lon").toString()));
		coord.setLat(Double.parseDouble(coordObject.get("lat").toString()));
		return coord;
	}

	public JSONArray getWeatherArray(JSONObject bodyResponse) throws JSONException {
		log.info("getting weatherArray");
		return bodyResponse.getJSONArray("weather");
	}

	public List<WeatherDTO> buildWeather(JSONArray weatherArray) {
		List<WeatherDTO> listWeather = new ArrayList<>();
		for (int i = 0; i < weatherArray.length(); i++) {
			WeatherDTO weather = new WeatherDTO();
			weather.setId(Integer.valueOf(weatherArray.getJSONObject(i).get("id").toString()));
			weather.setMain(weatherArray.getJSONObject(i).get("main").toString());
			weather.setDescription(weatherArray.getJSONObject(i).get("description").toString());
			weather.setIcon(weatherArray.getJSONObject(i).get("icon").toString());
			listWeather.add(weather);
		}
		return listWeather;
	}

	public JSONObject getMain(JSONObject bodyRequest) throws JSONException {
		log.info("getting main");
		return bodyRequest.getJSONObject("main");
	}

	public MainDTO buildMain(JSONObject mainObject) {
		MainDTO main = new MainDTO();
		main.setTemp(Operations.convertCelsius(mainObject.getDouble("temp")));
		main.setFeels_like(Operations.convertCelsius(mainObject.getDouble("feels_like")));
		main.setTemp_min(Operations.convertCelsius(mainObject.getDouble("temp_min")));
		main.setTemp_max(Operations.convertCelsius(mainObject.getDouble("temp_max")));
		main.setPressure(Integer.parseInt(mainObject.get("pressure").toString()));
		main.setHumidity(Integer.parseInt(mainObject.get("humidity").toString()));
		return main;
	}

	public JSONObject getClouds(JSONObject bodyRequest) throws JSONException {
		log.info("getting clouds");
		return bodyRequest.getJSONObject("clouds");
	}

	public CloudsDTO buildClouds(JSONObject cloudsObject) {
		CloudsDTO cloud = new CloudsDTO();
		cloud.setAll(Integer.valueOf(cloudsObject.get("all").toString()));
		return cloud;
	}

	public JSONObject getWind(JSONObject bodyRequest) throws JSONException {
		log.info("getting wind");
		return bodyRequest.getJSONObject("wind");
	}

	public WindDTO buildWind(JSONObject windObject) {
		WindDTO wind = new WindDTO();
		try {
			wind.setSpeed(windObject.get("speed").toString());
			wind.setDeg(windObject.get("deg").toString());
			if (windObject.has("gust")) {
				windObject.get("gust").toString();
			}
		} catch (Exception ex) {
			log.error("error: " + ex);
			wind = null;
		}
		return wind;
	}

	public JSONObject getSys(JSONObject bodyRequest) throws JSONException {
		log.info("getting sys");
		return bodyRequest.getJSONObject("sys");
	}

	public SysDTO buildSys(JSONObject sysObject) {
		SysDTO sys = new SysDTO();
		try {
			if (sysObject.has("id")) {
				sys.setId(Long.valueOf(sysObject.get("id").toString()));
			}
			sys.setType(Integer.valueOf(sysObject.get("type").toString()));
			sys.setCountry(sysObject.get("country").toString());
			sys.setSunrise(sysObject.get("sunrise").toString());
			sys.setSunset(sysObject.get("sunset").toString());
		} catch (Exception ex) {
			log.error("error: " + ex);
			sys = null;
		}
		return sys;
	}

	public com.checkWeather.dto.ResponseWeatherDTO buildResponse(JSONObject bodyResponse) {
		com.checkWeather.dto.ResponseWeatherDTO resp = new com.checkWeather.dto.ResponseWeatherDTO();

		// Coord
		JSONObject coordObject = this.getCoord(bodyResponse);
		CoordinatedDTO coord = this.buildCoord(coordObject);

		// Weather
		JSONArray weatherArray = this.getWeatherArray(bodyResponse);
		List<WeatherDTO> weathers = this.buildWeather(weatherArray);

		// Main
		JSONObject mainObject = this.getMain(bodyResponse);
		MainDTO main = this.buildMain(mainObject);

		// Wind
		JSONObject windObject = this.getWind(bodyResponse);
		WindDTO wind = this.buildWind(windObject);

		// Clouds
		JSONObject cloudsObject = this.getClouds(bodyResponse);
		CloudsDTO cloud = this.buildClouds(cloudsObject);

		// Sys
		JSONObject sysObject = this.getSys(bodyResponse);
		SysDTO sys = this.buildSys(sysObject);

		resp.setCoord(coord);
		resp.setWeather(weathers);
		resp.setMain(main);
		resp.setWind(wind);
		resp.setClouds(cloud);
		resp.setSys(sys);
		resp.setBase(bodyResponse.getString("base"));
		resp.setVisibility(String.valueOf(bodyResponse.getDouble("visibility")));
		resp.setDt(String.valueOf(bodyResponse.getLong("dt")));
		resp.setTimezone(bodyResponse.get("timezone").toString());
		resp.setId(bodyResponse.getLong("id"));
		resp.setName(bodyResponse.getString("name"));
		resp.setCod(bodyResponse.getInt("cod"));
		return resp;
	}

	@Override
	public ResponseServerDTO getHistoryService() {
		responseServerDTO = new ResponseServerDTO();
		List<HistoryDTO> listHistory = new ArrayList<>();
		Pageable topTen = PageRequest.of(0, 10);
		List<Ciudad> listCiudad = ciudadDao.findByOrderByFechaCreacionDesc(topTen);
		if (!listCiudad.isEmpty()) {
			for (Ciudad c : listCiudad) {
				HistoryDTO history = new HistoryDTO();
				long cont = climaDao.countByCiudad(c);
				history.setNombreCiudad(c.getNombre());
				history.setNumeroConsultas(cont);
				listHistory.add(history);
			}

			if (listHistory.isEmpty()) {
				responseServerDTO = new HistoryResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Error al realizar la consulta. Servidor No Disponible.");
			} else {
				responseServerDTO = new HistoryResponseDTO(HttpStatus.OK.value(), listHistory);
			}
		} else {
			responseServerDTO = new HistoryResponseDTO(HttpStatus.NOT_FOUND.value(),
					"Error, No se encuentra disponible el recurso.");
		}

		return responseServerDTO;
	}

}