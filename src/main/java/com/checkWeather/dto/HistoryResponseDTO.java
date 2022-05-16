package com.checkWeather.dto;

import java.io.Serializable;
import java.util.List;

public class HistoryResponseDTO extends ResponseServerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<HistoryDTO> historial;

	public HistoryResponseDTO(int codeResponse, List<HistoryDTO> historial) {
		super(codeResponse);
		this.historial = historial;
	}

	public HistoryResponseDTO(int codeResponse, String errorMessage) {
		super(errorMessage, codeResponse);
	}

	public List<HistoryDTO> getHistorial() {
		return historial;
	}

	public void setHistorial(List<HistoryDTO> historial) {
		this.historial = historial;
	}

}
