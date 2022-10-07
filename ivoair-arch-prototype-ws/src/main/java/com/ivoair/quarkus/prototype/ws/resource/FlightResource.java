package com.ivoair.quarkus.prototype.ws.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.ivoair.quarkus.prototype.api.dto.FlightDTO;
import com.ivoair.quarkus.prototype.api.service.FlightService;

/**
 * 
 * Resource for flight operations.
 *
 */
@Path("/1/flight")
public class FlightResource {

	@Inject
	FlightService flightService;

	@GET
	public List<FlightDTO> findAll() {
		return this.flightService.findFlights(null);
    }

	@GET
	@Path("/count")
    public Long count() {
		return this.flightService.countFlights();
    }

	@GET
	@Path("/{id}")
	public FlightDTO finById(@PathParam("id") final String id) {
		return this.flightService.findFlight(id);
    }

	@POST
	public FlightDTO insert(final FlightDTO flight) {
		final String id = this.flightService.createFlight(flight);
		flight.setId(id);
		return flight;
    }

	@PUT
	@Path("/{id}")
	public FlightDTO modify(@PathParam("id") final String id, final FlightDTO flight) {
		flight.setId(id);
		if (this.flightService.modifyFlight(flight)) {
			return finById(flight.getId());
        }
		return flight;

    }

	@DELETE
	@Path("/{id}")
	public String delete(@PathParam("id") final String id) {
		this.flightService.deleteFlight(id);
        return id;
    }

}
