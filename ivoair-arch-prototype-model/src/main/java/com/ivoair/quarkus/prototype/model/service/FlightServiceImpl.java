package com.ivoair.quarkus.prototype.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ivoair.quarkus.prototype.api.dto.FlightDTO;
import com.ivoair.quarkus.prototype.api.service.FlightService;
import com.ivoair.quarkus.prototype.model.entity.Flight;
import com.ivoair.quarkus.prototype.model.mapper.FlightMapper;
import com.ivoair.quarkus.prototype.model.repository.FlightRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link FlightService} implementation
 */
@ApplicationScoped
@Slf4j
public class FlightServiceImpl implements FlightService {

	@Inject
	FlightRepository flightRepository;

	@Inject
	FlightMapper flightMapper;

    @Override
	public boolean modifyFlight(final FlightDTO flight) {
		log.debug("Modify flight {}", flight.getId());
		Optional<Flight> result = this.flightRepository.findByIdOptional(Long.valueOf(flight.getId()));
		Flight entity = null;
		if (result.isPresent()) {
			entity = result.get();
			entity.setOrigin(flight.getOrigin());
			entity.setDestination(flight.getDestination());
			this.flightRepository.persist(entity);
        }
        return entity != null;
    }

    @Override
	public FlightDTO findFlight(final String id) {
		log.debug("Find flight {}", id);
		FlightDTO returnFlight = new FlightDTO();
		Optional<Flight> flight = this.flightRepository.findByIdOptional(Long.valueOf(id));
		if (flight.isPresent()) {
			returnFlight = this.flightMapper.asFlightDTO(flight.get());
		}
		return returnFlight;
    }

    @Override
	public List<FlightDTO> findFlights(final List<String> ids) {
		log.debug("Find flights {}", ids);
		List<Flight> result = new ArrayList<Flight>();
		PanacheQuery<Flight> findAll;
		if (ids == null || ids.isEmpty()) {
			findAll = this.flightRepository.findAll();
        } else {
			findAll = this.flightRepository.find("ids", ids);
        }
		findAll.list().forEach(result::add);
		return this.flightMapper.asFlightDTOs(result);

    }

    @Override
	public String createFlight(final FlightDTO flight) {
		log.debug("Create flight with id {}", flight.getId());
		this.flightRepository.persist(this.flightMapper.asFlight(flight));
		return flight.getId();

    }

    @Override
	public boolean deleteFlight(final String id) {
		log.debug("Delete flight {}", id);
		Optional<Flight> findFlight = this.flightRepository.findByIdOptional(Long.valueOf(id));
		if (findFlight.isPresent()) {
			this.flightRepository.delete(findFlight.get());
        }
		return findFlight.isPresent();

    }

    @Override
	public Long countFlights() {
		log.debug("Count flight");
		return this.flightRepository.count();
    }

}
