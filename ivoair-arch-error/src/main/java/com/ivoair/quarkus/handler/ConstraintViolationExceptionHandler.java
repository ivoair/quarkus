package com.ivoair.quarkus.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
 * ConstraintViolationException Handler
 *
 */
@Provider
@Slf4j
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException ex) {

		AppErrorResponseBean responseBean = new AppErrorResponseBean();
		responseBean.setSuccess(Boolean.FALSE);
		List<AppResponseError> errorList = new ArrayList<AppResponseError>();

		Set<ConstraintViolation<?>> result = ex.getConstraintViolations();
		for (ConstraintViolation<?> fieldError : result) {
			log.debug("ConstraintViolationException = {} = {}", ex.getMessage(),
					fieldError.getExecutableReturnValue().toString().concat(": ").concat(fieldError.getMessage()));
			AppResponseError error = new AppResponseError(AppErrorCode.ARQ_0001);
			error.setDescripcion(
					fieldError.getExecutableReturnValue().toString().concat(": ").concat(fieldError.getMessage()));
			errorList.add(error);
		}
		responseBean.setErrors(errorList);

		return Response.status(Status.BAD_REQUEST).entity(responseBean).build();
	}

}