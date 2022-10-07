package com.ivoair.quarkus.prototype.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * DTO for flight
 * 
 */
@Data
public class FlightDTO implements Serializable {

	private static final long serialVersionUID = -2974425621078183184L;

	private String id;

	@Size(max = 20)
    @NotEmpty
	private String origin;

	@Size(max = 20)
	@NotEmpty
	private String destination;

}
