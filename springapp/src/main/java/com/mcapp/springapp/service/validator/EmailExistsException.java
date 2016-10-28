package com.mcapp.springapp.service.validator;

public class EmailExistsException extends Throwable {

	private static final long serialVersionUID = 5850945899088180559L;

	public EmailExistsException(final String message) {
        super(message);
    }

}