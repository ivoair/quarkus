package com.ivoair.quarkus.logging;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

@Interceptor
public class LogInterceptor {

	static final AtomicReference<Object> LOG = new AtomicReference<Object>();

    @ServerRequestFilter(preMatching = true)
	public void preMatchingFilter(ContainerRequestContext requestContext) {
		LogBean logBean = new LogBean();

		// Get all fields from headers
		Map<String, String> tmpFields = new LinkedHashMap<String, String>();

		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		headers.entrySet().stream().forEach(e -> tmpFields.put(e.getKey(), e.getValue().get(0)));

		logBean.setLogFields(tmpFields);

		LogHolder.set(logBean);

	}

	@ServerResponseFilter
	public void afterCompletion(ContainerResponseContext responseContext) {
		LogHolder.remove();
	}

}
