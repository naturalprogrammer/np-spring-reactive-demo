package com.naturalprogrammer.reactivedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FooRoutes {
	
	@Bean
	public RouterFunction<ServerResponse> route(FooHandler fooHandler) {

		return RouterFunctions
			.route(RequestPredicates.GET("/foo-stream")
				.and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), fooHandler::fooStream);
	}
}
