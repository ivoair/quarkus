package com.ivoair.quarkus.exception;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 *
 * Bean for encapsulate errors in responses.
 *
 */
@Data
@ToString
public class AppErrorResponseBean implements Serializable {
	
	private static final long serialVersionUID = 50360618198372259L;
	
	private Boolean success = Boolean.FALSE;
	private List<AppResponseError> errors;
	
}
