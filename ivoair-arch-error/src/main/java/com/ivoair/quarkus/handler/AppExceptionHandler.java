package com.ivoair.quarkus.handler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ivoair.quarkus.exception.AppErrorCode;
import com.ivoair.quarkus.exception.AppErrorResponseBean;
import com.ivoair.quarkus.exception.AppException;
import com.ivoair.quarkus.exception.AppResponseError;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * AppException Handler
 *
 */
@Provider
@Slf4j
public class AppExceptionHandler implements ExceptionMapper<AppException> {

	@Override
	public Response toResponse(AppException ex) {

		AppErrorResponseBean responseBean = new AppErrorResponseBean();
		responseBean.setSuccess(Boolean.FALSE);
		List<AppResponseError> errorList = new ArrayList<AppResponseError>();

		AppResponseError error = new AppResponseError(AppErrorCode.of(((AppException) ex).getCode()),
				AppErrorCode.of(((AppException) ex).getCode()).getDescription());
		log.error("AppException = {} = {} = {} ", error.getCodigo().getCode(),
				AppErrorCode.of(error.getCodigo().getCode()).getDescription(), ex.getCause());
		errorList.add(error);
		responseBean.setErrors(errorList);

		return Response.status(Status.BAD_REQUEST).entity(responseBean).build();
	}

}