package com.bridgelabz.fundoonotes.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FundooException extends RuntimeException {

    private int statusCode;
	
	private String statusMessage;

	public FundooException(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	
}
