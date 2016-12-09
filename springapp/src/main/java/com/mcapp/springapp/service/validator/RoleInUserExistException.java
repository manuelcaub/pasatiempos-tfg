package com.mcapp.springapp.service.validator;

public class RoleInUserExistException extends Throwable  {

	private static final long serialVersionUID = -4898729725566939279L;
	
	public RoleInUserExistException(final String message) {
        super(message);
    }
}
