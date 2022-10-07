package com.ivoair.quarkus.prototype.model.repository;

import javax.enterprise.context.ApplicationScoped;

import com.ivoair.quarkus.prototype.model.entity.Flight;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FlightRepository implements PanacheRepository<Flight> {

}
