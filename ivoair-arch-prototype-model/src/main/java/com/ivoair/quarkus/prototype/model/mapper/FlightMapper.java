package com.ivoair.quarkus.prototype.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ivoair.quarkus.prototype.api.dto.FlightDTO;
import com.ivoair.quarkus.prototype.model.entity.Flight;

@Mapper
public interface FlightMapper {

	FlightDTO asFlightDTO(Flight src);

	Flight asFlight(FlightDTO src);

	List<FlightDTO> asFlightDTOs(List<Flight> src);

}
