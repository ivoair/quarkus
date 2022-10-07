package com.ivoair.quarkus.prototype.ws.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lombok.extern.slf4j.Slf4j;

@Path("/test")
@Slf4j
public class TestResource {

	@GET
	public String log() {
		log.trace("This is a Trace message");
		log.info("This is an Info message");
		log.debug("This is a Debug message");
		log.error("This is an Error message");
		log.warn("This is a Warn message");
		return "OK";
	}
}
