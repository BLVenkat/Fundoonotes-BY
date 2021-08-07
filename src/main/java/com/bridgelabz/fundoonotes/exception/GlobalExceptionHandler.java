package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoonotes.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(FundooException.class)
	public ResponseEntity<Response> handleFundooException(FundooException ex) {
		
		Response error = new Response(ex.getStatusCode(), ex.getStatusMessage(), null);
		return new ResponseEntity<Response>(error, HttpStatus.BAD_GATEWAY);
	}

	
}
