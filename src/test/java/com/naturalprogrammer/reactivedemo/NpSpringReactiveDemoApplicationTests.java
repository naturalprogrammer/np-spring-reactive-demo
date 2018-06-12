package com.naturalprogrammer.reactivedemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NpSpringReactiveDemoApplicationTests {
	
    private WebTestClient client;

    @Autowired
    public void setWebTestClient(ApplicationContext context) {

		client = WebTestClient.bindToApplicationContext(context).build();
	}

    /**
     * See https://docs.spring.io/spring/docs/5.1.0.BUILD-SNAPSHOT/spring-framework-reference/testing.html#webtestclient-stream
     */
	@Test
	public void testFooStream() {
		
		FluxExchangeResult<Foo> result = client.get().uri("/foo-stream")
	            .accept(MediaType.APPLICATION_STREAM_JSON)
	            .exchange()
	            .expectStatus().isOk()
	            .returnResult(Foo.class);
		
		Flux<Foo> fooFlux = result.getResponseBody();
		
		StepVerifier.create(fooFlux)
		   .assertNext(foo -> {
        	   
        	   assertEquals(0, foo.getId());
        	   assertEquals("Name 0", foo.getName());
           })
		   .assertNext(foo -> {
        	   
        	   assertEquals(1, foo.getId());
        	   assertEquals("Name 1", foo.getName());
           })
		   .thenCancel()
           .verify();
	}
}
