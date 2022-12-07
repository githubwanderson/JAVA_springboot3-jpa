package com.educandoweb.course.services.exceptions;

/**
 * Exception personalizada
 * @author WANDERSON
 *
 */
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}

}
