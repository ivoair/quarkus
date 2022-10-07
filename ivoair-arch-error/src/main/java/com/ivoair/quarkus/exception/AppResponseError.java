package com.ivoair.quarkus.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Object to represent a single error for responses.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponseError implements Serializable {

	private static final long serialVersionUID = 3909018099435882941L;
	
	private AppErrorCode codigo;
	private String descripcion;
	
	public AppResponseError(AppErrorCode error) {
		this.codigo = error;
		this.descripcion = error.getDescription(); 
	}

}
