package com.pc.springboot.exception;

import java.util.Date;

public class CustomErrorDetails {

	private String message;
	private Date timestamp;
	private String errordetails;
	public CustomErrorDetails(String message, Date timestamp, String errordetails) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.errordetails = errordetails;
	}
	public String getMessage() {
		return message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getErrordetails() {
		return errordetails;
	}
	
	
	
	

}
