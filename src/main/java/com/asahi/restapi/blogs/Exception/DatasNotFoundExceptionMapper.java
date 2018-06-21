package com.asahi.restapi.blogs.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.asahi.restapi.blogs.entity.ErrorMessage;

@Provider
public class DatasNotFoundExceptionMapper implements ExceptionMapper<DatasNotFoundException> {

	@Override
	public Response toResponse(DatasNotFoundException exception) {
		
		ErrorMessage error = new ErrorMessage(exception.getMessage(),404,"Some problem");
		return Response.status(Status.OK)
				.entity(error)
				.build();
	}

}
