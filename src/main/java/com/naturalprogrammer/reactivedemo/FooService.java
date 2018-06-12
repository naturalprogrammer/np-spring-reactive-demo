package com.naturalprogrammer.reactivedemo;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FooService {
	
	private static final Log log = LogFactory.getLog(FooService.class);
	
	public Flux<Foo> fooStream() {
		
		return Flux.interval(Duration.ofSeconds(1))
				.map(Foo::newInstance)
				.doOnNext(foo -> log.info("Emitting " + foo));
	}
}
