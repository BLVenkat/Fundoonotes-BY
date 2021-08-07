package com.bridgelabz.fundoonotes.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundooException extends RuntimeException {

    private int statusCode;
	
	private String statusMessage;
}
