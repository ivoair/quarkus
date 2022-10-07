package com.ivoair.quarkus.handler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ivoair.quarkus.exception.AppErrorCode;
import com.ivoair.quarkus.exception.AppErrorResponseBean;
import com.ivoair.quarkus.exception.AppResponseError;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * RuntimeException Handler
 *
 */
@Provider
@Slf4j
public class RuntimeExceptionHandler implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException ex) {

		AppErrorResponseBean responseBean = new AppErrorResponseBean();
		responseBean.setSuccess(Boolean.FALSE);
		List<AppResponseError> errorList = new ArrayList<AppResponseError>();

		log.error("ERROR RuntimeException = {} ", ex.getMessage(), ex);
		AppResponseError error = new AppResponseError(AppErrorCode.ARQ_0001, AppErrorCode.ARQ_0001.getDescription());
		errorList.add(error);
		responseBean.setErrors(errorList);

		return Response.status(Status.BAD_REQUEST).entity(responseBean).build();
	}

}