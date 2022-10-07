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
 * NullPointerException Handler
 *
 */
@Provider
@Slf4j
public class NullPointerExceptionHandler implements ExceptionMapper<NullPointerException> {

	@Override
	public Response toResponse(NullPointerException ex) {

		AppErrorResponseBean responseBean = new AppErrorResponseBean();
		responseBean.setSuccess(Boolean.FALSE);
		List<AppResponseError> errorList = new ArrayList<AppResponseError>();

		if (AppErrorCode.of(ex.getMessage()) != null) {
			log.debug("Controlled NullPointerException = {} ", ex.getMessage(),
					AppErrorCode.of(ex.getMessage()).getDescription());
			AppResponseError error = new AppResponseError(AppErrorCode.of(ex.getMessage()),
					AppErrorCode.of(ex.getMessage()).getDescription());
			errorList.add(error);
			responseBean.setErrors(errorList);
		} else {
			log.error("ERROR Uncontrolled NullPointerException = {} ", ex.getMessage(), ex);
			AppResponseError error = new AppResponseError(AppErrorCode.ARQ_0001, ex.getMessage());
			errorList.add(error);
			responseBean.setErrors(errorList);
		}

		return Response.status(Status.BAD_REQUEST).entity(responseBean).build();
	}

}