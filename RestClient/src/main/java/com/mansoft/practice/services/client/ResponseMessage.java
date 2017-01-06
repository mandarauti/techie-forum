package com.mansoft.practice.services.client;

import java.io.Serializable;

/**
 * @author mandar_auti
 *
 */
public class ResponseMessage implements Serializable{
	
	
	private String statusCode;
	private String errorCode;
	private String errorMessage;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
