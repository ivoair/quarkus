package com.ivoair.quarkus.config;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ConfigurationTask {

	@ConfigProperty(name = "configuration.application")
	String application;

	@ConfigProperty(name = "configuration.environment")
	String environment;

	@ConfigProperty(name = "configuration.configuration")
	String configuration;

	@ConfigProperty(name = "configuration.clientconfigurationversion")
	String clientconfigurationversion;

	@ConfigProperty(name = "configuration.clientid")
	String clientid;

//	@Inject
//	@RestClient
//	ConfigurationService configurationService;

	@Scheduled(every = "10s")
	void validateConfiguration() {

		log.info("Validating configuration changes}");

//		Response response = configurationService.getConfiguration(application, environment, configuration,
//				clientconfigurationversion,
//				clientid);

//		log.info("Cloud Config Server response: {}", response.getStatus());

	}

}
