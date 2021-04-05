package com.example.auth.model;

import java.sql.Timestamp;


public class Response {				
	
	private Timestamp timestamp;		
	private String data;		
	private int respondeCode;		
	private String status;
		
	public Response() {
		super();
	}
	public Response(Timestamp timestamp, String data, int respondeCode, String status) {
		super();
		this.timestamp = timestamp;
		this.data = data;
		this.respondeCode = respondeCode;
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getRespondeCode() {
		return respondeCode;
	}
	public void setRespondeCode(int respondeCode) {
		this.respondeCode = respondeCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}

	