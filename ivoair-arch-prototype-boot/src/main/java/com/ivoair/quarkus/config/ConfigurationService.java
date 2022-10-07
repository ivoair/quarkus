package com.ivoair.quarkus.config;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface ConfigurationService {

	@GET
	@Path("/applications/{application}/environments/{environment}/configurations/{configuration}?client_configuration_version={clientconfigurationversion}&client_id={clientid}")
	Response getConfiguration(@PathParam("application") String application,
			@PathParam("environment") String environment,
			@PathParam("configuration") String configuration,
			@PathParam("clientconfigurationversion") String clientconfigurationversion,
			@PathParam("clientid") String clientid);

}
