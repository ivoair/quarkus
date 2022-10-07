package com.ivoair.quarkus.prototype.api.service;

import java.util.List;

import javax.validation.Valid;

import com.ivoair.quarkus.prototype.api.dto.FlightDTO;

/**
 * 
 * Service layer with operations for {@link FlightDTO}
 *
 */
public interface FlightService {

    /**
	 * Create a new flight
	 * 
	 * @param flight flight to create
	 * @return the identifier of the flight
	 */
	String createFlight(@Valid final FlightDTO flight);

    /**
	 * Delete the flight with the identifier {@code id}
	 * 
	 * @param id identifier of the flight to delete
	 * @return true if the flight has been deleted, false in other case.
	 */
	boolean deleteFlight(final String id);

    /**
	 * Modify the flight {@code flight}
	 * 
	 * @param flight flight to modify
	 * @return true if the flight has been modified, false in other case
	 */
	boolean modifyFlight(@Valid final FlightDTO flight);

    /**
	 * Find the flight with identifier {@code id}
	 * 
	 * @param id id of the flight to find
	 * @return the flight with identifier {@code id}
	 */
	FlightDTO findFlight(final String id);

    /**
	 * Find the flights which identifiers are contained in {@code ids}
	 * 
	 * @param ids identifiers of the flights to find
	 * @return flights which identifiers are contained in {@code ids}
	 */
	List<FlightDTO> findFlights(final List<String> ids);

    /**
	 * Return the total number of flights
	 * 
	 * @return the total flights
	 */
	Long countFlights();

}
