package com.sk.korisnicki.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidException extends CustomException {

	public NotValidException(String message) {
        super(message, ErrorCode.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE);
    }
	
}
