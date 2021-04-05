package com.example.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.auth.model.Response;
import com.example.auth.model.Time;

@ControllerAdvice	
public class ExceptionGlobal {	
	
	Response result;	
	
	@ExceptionHandler(RuntimeException.class)		
	public ResponseEntity<Response> runtimeException(RuntimeException e) {			
		result = new Response(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), 500, "Error");			
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);		
	}				
	
	@ExceptionHandler(Exception.class)		public ResponseEntity<Response> exception(Exception e) {			
		result = new Response(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), 500, "Error");			
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);		
	}	
}
