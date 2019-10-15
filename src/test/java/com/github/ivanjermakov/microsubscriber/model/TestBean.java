package com.github.ivanjermakov.microsubscriber.model;

import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TestBean {

	public TestBean() {
	}

	@Subscribe(
			baseUrl = "",
			uri = "",
			responseType = Object.class
	)
	private Flux f;

}
