package com.naturalprogrammer.reactivedemo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class FooHandler {
	
	private FooService fooService;
	
	public FooHandler(FooService fooService) {

		this.fooService = fooService;
	}

	public Mono<ServerResponse> fooStream(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
			.body(fooService.fooStream(), Foo.class);
	}
}
