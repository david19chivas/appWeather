package com.checkWeather.dto;

import java.io.Serializable;

public class ResponseServerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private int codeResponse;
	
	
	public ResponseServerDTO() {
		super();
	}


	public ResponseServerDTO(String errorMessage, int codeResponse) {
		super();
		this.errorMessage = errorMessage;
		this.codeResponse = codeResponse;
	}

	
	public ResponseServerDTO(int codeResponse) {
		super();
		this.codeResponse = codeResponse;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public int getCodeResponse() {
		return codeResponse;
	}


	public void setCodeResponse(int codeResponse) {
		this.codeResponse = codeResponse;
	}
	
}
