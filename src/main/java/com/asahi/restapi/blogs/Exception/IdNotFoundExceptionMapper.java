package com.asahi.restapi.blogs.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.asahi.restapi.blogs.entity.ErrorMessage;

@Provider
public class IdNotFoundExceptionMapper implements ExceptionMapper<IdNotFoundException> {

	@Override
	public Response toResponse(IdNotFoundException exception) {
		ErrorMessage error = new ErrorMessage(exception.getMessage(),500,"Some Problem..");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(error)
				.build();
	}

}
