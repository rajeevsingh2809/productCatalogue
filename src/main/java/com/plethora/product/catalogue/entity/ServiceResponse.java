package com.plethora.product.catalogue.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author rajeev.k.singh
 *
 */
public class ServiceResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private String status;
	private String message;

	private ServiceResponse() {
		timestamp = new Date();
	}

	public ServiceResponse(String status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ServiceResponse [timestamp=" + timestamp + ", status=" + status + ", message=" + message + "]";
	}

}
