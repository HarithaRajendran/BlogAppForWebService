package com.asahi.restapi.blogs.Exception;

public class IdNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public IdNotFoundException(String message){
		super(message);
	}
	
	

}
